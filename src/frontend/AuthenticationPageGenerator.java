package frontend;

public class AuthenticationPageGenerator {
    private static String startForm = "<form method='POST'>";
    private static String inputName = "<p>Введите своё имя: </p> <input name='name' type='text'/>";
    private static String endForm = "<p><input type='submit'></p></form>";

    private static String getInputWithSessionId(int sessionId) {
        return "<input name='session_id' value='" + sessionId + "' type='text'/ hidden>";
    }

    public static String getPage() {
        return startForm + inputName + endForm;
    }

    public static String getPageWithSessionId(int sessionId) {
        return startForm + inputName + getInputWithSessionId(sessionId) + endForm;
    }

    public static String getPageWaitAuthorization(String name,int sessionId) {
        return "<h1>Wait for authorization, " + name + "</h1>" + startForm + getInputWithSessionId(sessionId) + endForm;
    }
}
