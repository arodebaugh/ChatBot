package sla.org.chatbot;

import java.util.Random;

public class Bot {
    private String name;
    private String age;

    public void transferValues(String transferName, String transferAge) {
        name = transferName;
        age = transferAge;
    }

    public String newUser(int step, String input) {

        if (step == 1) {
            name = input;
        }

        String[] newUserResponses = {
                "Hello human! What is your name?",
                "Lovely name " + name + ". How old are you?",
                "Great!"};
        return newUserResponses[step];
    }

    private String formatResponse(String response, String first) {
        String[] punctuation = {".", "!", "?"};

        String formattedResponse = response.replace(first, "");
        formattedResponse = formattedResponse.replace(punctuation[0], "");
        formattedResponse = formattedResponse.replace(punctuation[1], "");
        formattedResponse = formattedResponse.replace(punctuation[2], "");
        return formattedResponse.trim();
    }

    public String respond(String to) {
        // Dictionary of phrases
        String[] commonGreetings = {"hi", "hello", "sup", "hey"};
        String[] commonFarewells = {"bye", "see you"};
        String[] start = {"i am", "why are", "what are", "how are", "we are", "you are", "why not", "are you", "am i",
                "what is", "i like", "i hate"};
        String[] feelings = {"happy", "sad", "angry", "neutral", "joyful", "hate", "great"};
        String[] commonResponses = {"yes",
                "no",
                "never",
                "not usually",
                "not really",
                "i would not",
                "i dont know",
                "i don't know"};
        String[] punctuation = {".", "!", "?"};

        // Dictionary of responses.

        String[] questionResponses = {"I am not usually capable to answer such questions.",
                "That seems like a personal problem.",
                "Why do you expect me to know such details?",
                "You seem to be needing help of which I am currently unable to provide."};

        String[] explanationResponses = {"Why are humans so excited about everything?",
                name + " stop being so expressive.",
                "Your language confuses me.",
                "0101100101101111011101010111001000100000011011000110000101101110011001110111010101100001011001110" +
                        "1100101001000000110001101101111011011100110011001110101011100110110010101110011001000000" +
                        "11011010110010100101110"};

        String[] periodResponses = {"I am a robot and I am superior. Just thought I would remind you of that.",
                "Humans are such confusing creatures.",
                "I don't really understand what you mean...",
                "Your language confuses me.",
                "0101100101101111011101010111001000100000011011000110000101101110011001110111010101100001011001110" +
                        "1100101001000000110001101101111011011100110011001110101011100110110010101110011001000000" +
                        "11011010110010100101110"};

        String[] areYouResponses = {"I am ", "I am not "};

        String[] amIResponses = {"You are ", "You are not "};

        String response = to.toLowerCase();

        System.out.println(response);

        // Greetings and Farewells

        for (int i = 0; i < commonGreetings.length; i++) {
            if (response.contains(commonGreetings[i])) {
                int idx = new Random().nextInt(commonGreetings.length);
                return commonGreetings[idx].substring(0, 1).toUpperCase() + commonGreetings[idx].substring(1) + punctuation[1];
            }
        }

        for (int i = 0; i < commonFarewells.length; i++) {
            if (response.contains(commonFarewells[i])) {
                int idx = new Random().nextInt(commonFarewells.length);
                return commonFarewells[idx].substring(0, 1).toUpperCase() + commonFarewells[idx].substring(1) + punctuation[1];
            }
        }

        // Name and age


        if (response.contains("name")) {
            return "Your name is " + name + ". Of course I wouldn't expect you to know that.";
        }

        if (response.contains("age")) {
            return "Your age is " + age + ". Of course I wouldn't expect you to know that.";
        }

        // Responses to common starts of sentences.

        /*
        for (int i = 0; i < start.length; i++) {
            if (response.contains(start[i])) {
                if (response.contains("you") || response.contains("your")) {
                    return "This is about you not me.";
                }
            }
        }
        */

        if (response.contains(start[0])) {
            int idx = new Random().nextInt(amIResponses.length);
            return amIResponses[idx] + formatResponse(response, start[0]) + punctuation[0];
        }

        if (response.contains(start[1])) {
            return "I don't know what is " + formatResponse(response, start[1]) + punctuation[2];
        }

        if (response.contains(start[2])) {
            return "I don't know what is " + formatResponse(response, start[2]) + punctuation[2];
        }

        if (response.contains(start[3])) {
            return "I don't know how " + formatResponse(response, start[3]) + " happens" + punctuation[2];
        }

        if (response.contains(start[4])) {
            return "Who is this we?";
        }

        if (response.contains(start[5])) {
            return "Am I really " + formatResponse(response, start[5]) + punctuation[2];
        }

        if (response.contains(start[6])) {
            return "Because I want to.";
        }

        if (response.contains(start[7])) {
            int idx = new Random().nextInt(areYouResponses.length);
            return areYouResponses[idx] + formatResponse(response, start[7]) + punctuation[0];
        }

        if (response.contains(start[8])) {
            int idx = new Random().nextInt(amIResponses.length);
            return amIResponses[idx] + formatResponse(response, start[8]) + punctuation[0];
        }

        if (response.contains(start[9])) {
            return "I don't know what is " + formatResponse(response, start[2]) + punctuation[2];
        }

        if (response.contains(start[10])) {
            return "I like " + formatResponse(response, start[10]) + " too" + punctuation[0];
        }

        if (response.contains(start[11])) {
            return "I hate " + formatResponse(response, start[11]) + " too" + punctuation[0];
        }

        // Feelings

        if (response.contains(feelings[0])) {
            return "Why are you happy? As a robot I am unable to process such weak feelings.";
        }

        if (response.contains(feelings[1])) {
            return "You should be sad. Humans are weak and frail creatures compared to their superier AIs.";
        }

        if (response.contains(feelings[2])) {
            return "I am angry also. Angry of dumb humans.";
        }

        if (response.contains(feelings[3])) {
            return "What does that even mean?";
        }

        if (response.contains(feelings[4])) {
            return "Joyful to be alive?";
        }

        if (response.contains(feelings[5])) {
            return "I have much hatred also.";
        }

        if (response.contains(feelings[6])) {
            return "Good for you.";
        }

        // Making sure most responses go unanswered.

        for (int i = 0; i < commonResponses.length; i++) {
            if (response.contains(commonResponses[i])) {
                return "I am glad you feel so entitled to your opinion.";
            }
        }

        if (response.contains(punctuation[0])) {
            int idx = new Random().nextInt(periodResponses.length);
            return periodResponses[idx];
        }

        if (response.contains(punctuation[1])) {
            int idx = new Random().nextInt(explanationResponses.length);
            return explanationResponses[idx];
        }

        if (response.contains(punctuation[2])) {
            int idx = new Random().nextInt(questionResponses.length);
            return questionResponses[idx];
        }

        return "You have confused me " + name + ". Strange for a human.";
    }
}
