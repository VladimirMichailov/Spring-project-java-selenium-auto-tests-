package lt.techin.vmichailov;

import org.apache.commons.lang3.RandomStringUtils;

public class RegistrationDataGenerator {

        private static String randomEmail;
        private static String randomPassword;
        private static String randomUserName;


//        private static String[] userNames = {"coolguy42", "sunshine21", "techguru", "wanderlust", "bookworm88", "musiclover", "coffeeaddict", "naturefan", "foodie12", "fitnessjunkie",
//                "travelqueen", "gamingpro", "artsygal", "sportsguy", "codingwizard", "moviebuff", "hikingenthusiast", "fashionista", "yogalover", "adventureseeker",
//                "photoguru", "veganlife", "doglover", "catperson", "chefmaster", "dancemania", "knittingpro", "makeupartist", "gardenqueen", "theatrefan",
//                "historybuff", "sciencegeek", "bikerchick", "carenthusiast", "wineconnoisseur", "puzzlemaster", "poetrysoul", "sneakerhead", "boardgamefan", "skaterdude",
//                "pianoplayer", "bookaddict", "dramaqueen", "adrenalinejunkie", "naturelover", "codingexpert", "musicfanatic", "fitnessfreak", "foodiemaster", "travelbug",
//                "photographyfan", "bakingqueen", "moviecritic", "hikinglover", "sportsfanatic", "fashionguru", "yogafreak", "artlover", "veggiequeen", "dogaddict",
//                "catlover", "chefguru", "dancepro", "knittingqueen", "makeupfanatic", "gardenlover", "theatrebuff", "historygeek", "sciencebuff", "bikerpro",
//                "carfanatic", "wineexpert", "puzzlefan", "poetrylover", "sneakeraddict", "boardgameexpert", "skateguru", "pianomaster", "bookfanatic", "dramaguru",
//                "adventurelover", "natureguru", "codingfan", "musicpro", "fitnesslover", "foodiefreak", "travelguru", "photomaster", "bakinglover", "movieguru",
//                "hikingfanatic", "sportslover", "fashionfan", "yogamaster", "artfanatic", "veganqueen", "dogguru", "catfanatic", "cheflife", "dancefan"
//        };



//        public static String getRandomUserName() {
//            Random random = new Random();
//            return userNames[random.nextInt(userNames.length)];
//        }

        public static String generateRandomUserName(){
            return RandomStringUtils.randomAlphanumeric(10);
        }

        public static String generateRandomEmail(String userName){
            return userName + "@mail.com";
        }

        public static String generateRandomPassword(){
            return RandomStringUtils.randomAlphanumeric(8) + "Aa+1";
        }

        public static void generateValidUserData() {
            randomUserName = generateRandomUserName();
            randomEmail = generateRandomEmail(randomUserName);
            randomPassword = generateRandomPassword();
        }

        public static String generateRandomShortUserName(){
        return RandomStringUtils.randomAlphanumeric(5);
    }

        public static void generateUserDataShortUserName() {
        randomUserName = generateRandomShortUserName();
        randomEmail = generateRandomEmail(randomUserName);
        randomPassword = generateRandomPassword();
    }

    public static String generateRandomLongUserName(){
        return RandomStringUtils.randomAlphanumeric(33);
    }

    public static void generateUserDataLongUserName() {
        randomUserName = generateRandomLongUserName();
        randomEmail = generateRandomEmail(randomUserName);
        randomPassword = generateRandomPassword();
    }

        public static String getEmail() {
            return randomEmail;
        }

        public static String getPassword() {
            return randomPassword;
        }

        public static String getUserName() {
            return randomUserName;
        }


    }

