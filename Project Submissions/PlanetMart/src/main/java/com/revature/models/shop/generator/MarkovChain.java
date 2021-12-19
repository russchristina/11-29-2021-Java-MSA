package com.revature.models.shop.generator;

import javax.imageio.ImageTranscoder;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkovChain {

    File nouns = new File("C:\\Users\\Bravo\\Documents\\gitRepos\\11-29-2021-Java-MSA\\Project Submissions\\PlanetMart\\src\\main\\resources\\nouns.txt");
    File adjectives = new File("C:\\Users\\Bravo\\Documents\\gitRepos\\11-29-2021-Java-MSA\\Project Submissions\\PlanetMart\\src\\main\\resources\\adjectives.txt");
    File connectives = new File("C:\\Users\\Bravo\\Documents\\gitRepos\\11-29-2021-Java-MSA\\Project Submissions\\PlanetMart\\src\\main\\resources\\connectives.txt");
    File verbs = new File("C:\\Users\\Bravo\\Documents\\gitRepos\\11-29-2021-Java-MSA\\Project Submissions\\PlanetMart\\src\\main\\resources\\verbs.txt");
    File pronouns = new File("C:\\Users\\Bravo\\Documents\\gitRepos\\11-29-2021-Java-MSA\\Project Submissions\\PlanetMart\\src\\main\\resources\\pronouns.txt");
    File randomStart = new File("C:\\Users\\Bravo\\Documents\\gitRepos\\11-29-2021-Java-MSA\\Project Submissions\\PlanetMart\\src\\main\\resources\\randomPhraseBegin.txt");

    public static void main(String[] args) {


        MarkovChain markovChain = new MarkovChain();

        StringBuilder randomParagraph = markovChain.generateParagraph();
        System.out.println(randomParagraph);
    }

    private StringBuilder generateParagraph(){
        StringBuilder paragraph = new StringBuilder();
        for(int i = 0; i < (int)(Math.random()*12)+6 ; i++){
            paragraph.append(generateSentence());
        }

        return paragraph;
    }

    private StringBuilder generateStartOfSentence(){
        List<String> randomStartList = getWords(randomStart);
        return new StringBuilder(randomStartList.get((int)(Math.random()*randomStartList.size())) + " ");
    }

    private StringBuilder generateSentence() {

        List<String> nounList = getWords(nouns);
        List<String> adjectivesList = getWords(adjectives);
        List<String> connectivesList = getWords(connectives);
        List<String> verbList = getWords(verbs);
        List<String> pronounList = getWords(pronouns);

        StringBuilder sentence = new StringBuilder();
        int randomWordType = 0;
        boolean previousNoun = false;
        boolean previousAdjective = false;
        boolean previousConnective = false;
        boolean previousPronoun = false;
        boolean previousVerb = false;

        int nounProb = 30;
        int pronounProb = 30;
        int connectiveProb = 10;
        int adjectiveProb = 15;
        int verbProb = 15;
        for(int i = 0; i < (int)(Math.random()* 20) + 10; i++){

            if(previousNoun){
                nounProb = 0;
                pronounProb = 20;
                connectiveProb = 50;
                adjectiveProb = 0;
                verbProb = 30;
            }
            if(previousAdjective){
                nounProb = 70;
                pronounProb = 0;
                connectiveProb = 0;
                adjectiveProb = 0;
                verbProb = 30;
            }
            if(previousConnective){
                nounProb = 10;
                pronounProb = 30;
                connectiveProb = 0;
                adjectiveProb = 30;
                verbProb = 30;
            }
            if(previousPronoun){
                nounProb = 20;
                pronounProb = 25;
                connectiveProb = 15;
                adjectiveProb = 20;
                verbProb = 20;
            }
            if(previousVerb){
                nounProb = 30;
                pronounProb = 30;
                connectiveProb = 20;
                adjectiveProb = 20;
                verbProb = 0;
            }


            randomWordType = (int)(Math.random()*100);
            if(randomWordType >=0 && randomWordType < nounProb){
                sentence.append(nounList.get((int)(Math.random()*nounList.size())));
                if(previousConnective)sentence.append("'s");
                if(previousAdjective)sentence.append("s");
                if(previousVerb)sentence.append(",");
                sentence.append(" ");

                previousNoun = true;
                previousAdjective = false;
                previousConnective = false;
                previousPronoun = false;
                previousVerb = false;

            } else if(randomWordType >= nounProb && randomWordType < (nounProb+pronounProb)){
                sentence.append(pronounList.get((int)(Math.random()*pronounList.size())));
                if(previousPronoun){
                    if((int)Math.random()*6>3) sentence.append(",");
                }
                sentence.append(" ");

                previousNoun = false;
                previousAdjective = false;
                previousConnective = false;
                previousPronoun = true;
                previousVerb = false;

            } else if(randomWordType >= (nounProb+pronounProb) && randomWordType < (nounProb+pronounProb+connectiveProb)){
                sentence.append(connectivesList.get((int)(Math.random()*connectivesList.size())));
                sentence.append(" ");

                previousNoun = false;
                previousAdjective = false;
                previousConnective = true;
                previousPronoun = false;
                previousVerb = false;

            } else if(randomWordType >= (nounProb+pronounProb+connectiveProb) && randomWordType < (nounProb+pronounProb+connectiveProb+adjectiveProb)){
                sentence.append(adjectivesList.get((int)(Math.random()*adjectivesList.size())));
                sentence.append(" ");

                previousNoun = false;
                previousAdjective = true;
                previousConnective = false;
                previousPronoun = false;
                previousVerb = false;


            } else if(randomWordType >= (nounProb+pronounProb+connectiveProb+adjectiveProb) && randomWordType< (nounProb+pronounProb+connectiveProb+adjectiveProb+verbProb)){
                sentence.append(verbList.get((int)(Math.random()*verbList.size())));
                if(previousAdjective){
                    if((int)(Math.random()*10)>7) sentence.append("!");
                }
                sentence.append(" ");
                previousNoun = false;
                previousAdjective = false;
                previousConnective = false;
                previousPronoun = false;
                previousVerb = true;
            }

        }

        sentence.append("\n");

        return generateStartOfSentence().append(sentence);
    }

    public List<String> getWords(File file){

        List<String> nounsList = new ArrayList<>();
        try(FileReader reader = new FileReader(file); BufferedReader buffReader = new BufferedReader(reader)) {
            //Again, this is more recommended for reading in bytes for images.
//			FileInputStream fis = new FileInputStream(myFile);

            while(buffReader.ready()) {
                nounsList.add(buffReader.readLine());
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nounsList;
    }

}
