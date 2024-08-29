package mg.lahatra3;

import mg.lahatra3.utils.Env4jUtils;

public class WisedataApplication {
    public static void main(String[] args) {
        Env4jUtils env4JUtils = new Env4jUtils();
        env4JUtils.load();

        WisedataRunner wisedataRunner = new WisedataRunner();
        wisedataRunner.run();
    }
}