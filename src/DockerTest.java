import com.spotify.docker.client.*;
import com.spotify.docker.client.messages.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DockerTest {
    public static void main(String[] args) throws DockerException, InterruptedException, DockerCertificateException {
        // Create a client based on DOCKER_HOST and DOCKER_CERT_PATH env vars
        final DockerClient docker = DefaultDockerClient.fromEnv().build();

// Pull an image
        docker.pull("syntaxdockertest/syntaxnet");

        // todo OR
// Pull an image from a private repository
// Server address defaults to "https://index.docker.io/v1/"
//        AuthConfig authConfig = AuthConfig.builder().email("foo@bar.com").username("foobar")
//                .password("secret-password").serverAddress("https://myprivateregistry.com/v1/").build();
//        docker.pull("foobar/busybox-private:latest", authConfig);

// You can also set the AuthConfig for the DockerClient instead of passing everytime you call pull()
//        DockerClient docker = DefaultDockerClient.fromEnv().authConfig(authConfig).build();

// Bind container ports to host ports
        final String[] ports = {"80", "22"};
        final Map<String, List<PortBinding>> portBindings = new HashMap<String, List<PortBinding>>();
        for (String port : ports) {
            List<PortBinding> hostPorts = new ArrayList<PortBinding>();
            hostPorts.add(PortBinding.of("0.0.0.0", port));
            portBindings.put(port, hostPorts);
        }

// Bind container port 443 to an automatically allocated available host port.
        List<PortBinding> randomPort = new ArrayList<PortBinding>();
        randomPort.add(PortBinding.randomPort("0.0.0.0"));
        portBindings.put("443", randomPort);

        final HostConfig hostConfig = HostConfig.builder().portBindings(portBindings).build();

// Create container with exposed ports
        final ContainerConfig containerConfig = ContainerConfig.builder()
                .hostConfig(hostConfig)
                .image("syntaxdockertest/syntaxnet").exposedPorts(ports)
                .cmd("sh", "-c", "while :; do sleep 1; done")
                .build();

        final ContainerCreation creation = docker.createContainer(containerConfig);
        final String id = creation.id();

// Inspect container
        final ContainerInfo info = docker.inspectContainer(id);

// Start container
        docker.startContainer(id);

// Exec command inside running container with attached STDOUT and STDERR
        final String[] command = {"bash", "-c", "ls"};
        final String execId = docker.execCreate(
                id, command, DockerClient.ExecCreateParam.attachStdout(),
                DockerClient.ExecCreateParam.attachStderr());
        final LogStream output = docker.execStart(execId);
        final String execOutput = output.readFully();

// Kill container
        docker.killContainer(id);

// Remove container
        docker.removeContainer(id);

// Close the docker client
        docker.close();
    }
}
