package util;

public class IdGenerator {
    private static int idCaptcha = 1;
    private static int userId = 1;
    private static int avatarId = 1;

    public static int getIdCaptcha() {
        return idCaptcha++;
    }

    public static int getAvatarId() {
        return avatarId++;
    }

    public static int getUserId() {
        return userId++;
    }
}
