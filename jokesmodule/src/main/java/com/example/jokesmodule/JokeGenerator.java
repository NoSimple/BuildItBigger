package com.example.jokesmodule;

import java.util.Random;

public final class JokeGenerator {

    private static String[] jokesList = {
            "Google request: How to disable auto correct in wife?",
            "When I was young there was only 25 letters in the Alphabet? Nobody knew why.",
            "Siri, who is your boyfriend? I'll leave the relations to the databases.",
            "When I was a kid, my teacher looked my way and said: Name two pronouns. I said: Who, me?",
            "Yes, money cannot buy you happiness, but I’d still feel a lot more comfortable crying in a new BMW than on a bike.",
            "A computer once beat me at chess, but it was no match for me at kick boxing.",
            "A user interface is like a joke. If you have to explain it, it's not that good."
    };

    public static String getJoke() {
        Random random = new Random();
        return jokesList[random.nextInt(jokesList.length)];
    }
}