import java.io.*;
import java.util.*;
 
 class Milestone4_Algorithm {
   
    public static void keywordReplacement(String[] inputArray){
        // Array that stores a collection of tweets
        String[] arrOfTweets = inputArray;
        //Hash table of abbreviations with their non abbreviated forms
        Hashtable<String, String> abbreviations = new Hashtable<String, String>();
        abbreviations.put("lol", "Laugh out loud");
        abbreviations.put("omg", "Oh my gosh");
        abbreviations.put("ttyl", "Talk to you later");
    
        // Creating first loop to iterate through array of tweets
        for(int i = 0; i < arrOfTweets.length; i++){
            // Split the tweets into individual words
            String[] tweets = arrOfTweets[i].split(" ");
                //loops through all of the pieces of the tweet
                for(int k = 0; k < tweets.length; k++){
                    if(abbreviations.containsKey(tweets[k])){
                        tweets[k] = abbreviations.get(tweets[k]);
                    }               
                }
            // Joining words back
            String joinedTweet = String.join(" ", tweets);  
            arrOfTweets[i] = joinedTweet;
            
        }

    }

    public static void main(String[] args) throws Exception {
        //run time for all 108 runs
        long[] runTime = new long[108];
        //run time index
        int runTimeIndx = 0;
        //test with the data amount increaseing by 10 each time
        for(int dataAmount = 1000; dataAmount < 108001; dataAmount = dataAmount + 1000){
            //total amount of time to calculate average for this data set
            long sumTime = 0;
            //run 10 times to get an average time
            for(int i = 0; i < 10; i++){
                //import file contents
                FileReader fr = new FileReader("com.AlienKing.LongestNight.csv");
                BufferedReader br = new BufferedReader(fr);
                String s;
                //assign a line to a slot in the array
                //increasing the amount of lines analyzed each time
                String [] inputArray = new String[dataAmount];
                int j = 0;
                while((s = br.readLine()) != null && j < inputArray.length) {
                    inputArray[j] = s;
                    //System.out.println(s);
                    j++;
                }
    
                br.close();
                //include run time of algorithm
                long startTime, endTime, totalTime;
                startTime = System.currentTimeMillis();
                keywordReplacement(inputArray);
                endTime = System.currentTimeMillis();
                totalTime = endTime - startTime;
                sumTime = sumTime + totalTime;
                //System.out.println("run: " + i + " " + totalTime);
    
            }
            //add run time to run time array
            runTime[runTimeIndx] = sumTime/10;
            runTimeIndx++;

        }
        
        //write to json file for python analysis
        String line = Arrays.toString(runTime);
        try {
            FileWriter myWriter = new FileWriter("averages.json");
            myWriter.write(line);
            myWriter.close();
            System.out.println(line);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("DONE");

    }   
    



}
