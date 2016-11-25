package neo4jTest;

import org.neo4j.driver.v1.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessing {
    public TextProcessing() {

    }

    public static void main(String[] args) throws InterruptedException {
        TextProcessing textProcessing = new TextProcessing();
//        textProcessing.replaceNonCharSymbolsOnSpace();
//        textProcessing.withTx();

    }

    public void withoutTx() throws InterruptedException {


        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j"));
        Session session = driver.session();
        for (int i = 0; i < 1000; i++) {
            int count = 1000;
            long begin = System.currentTimeMillis();

            for (int j = 0; j < count; j++) {
                session.run("CREATE (a:Person {id:" + i + ", name:'unknown'})");
            }
            if (session.isOpen()) {
                long end = System.currentTimeMillis();
                System.out.print("Inserting " + (double) count / ((double) (end - begin) / count) + " nodes per second.\n");
            }
        }
        session.close();
        driver.close();
    }

    public void withTx() {

        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j"));
        Session session = driver.session();
        String query = "CREATE (a:Person {id:{id}, name:'{name}'})";
        for (int i = 0; i < 1000; i++) {
            int count = 1000;
            long begin = System.currentTimeMillis();

            try (Transaction tx = session.beginTransaction()) {
                for (int j = 0; j < count; j++) {
//                    tx.run("CREATE (a:Person {id:" + i + ", name:'unknown'})");
                    tx.run(query, Values.parameters("id", i, "name", "unknown"));
                }
//                StatementResult result = tx.run("MATCH (a:Person) RETURN a.name AS name");
//                while (result.hasNext()) {
//                    Record record = result.next();
//                    System.out.println(record.get("title").asString() + " - " + record.get("name").asString());
//                }
                tx.success();
            }
            long end = System.currentTimeMillis();
            System.out.print("Inserting " + (double) count / ((double) (end - begin) / count) + " nodes per second.\n");
        }
        session.close();
        driver.close();
    }


    public void replaceNonCharSymbolsOnSpace() {
        String regex = "([^\\w\\s]+)";
        Pattern p = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);

        String text = "Папа пошел в лес в 3 часа ночи.fsd 90-0-++-";
        // get a matcher object
        Matcher m = p.matcher(text);

        int count = 0;

        while(m.find()) {
            count++;
            System.out.println("Match number "+count);
            System.out.println("start(): "+m.start());
            System.out.println("end(): "+m.end());
            int x = 1;
        }
        text = m.replaceAll(" ");
        System.out.println(text);
    }
}
