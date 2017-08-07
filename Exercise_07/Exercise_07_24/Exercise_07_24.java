/*********************************************************************************
* (Simulation: coupon collector’s problem) Coupon collector is a classic         *
* statistics problem with many practical applications. The problem is to pick    *
* objects from a set of objects repeatedly and find out how many picks are       *
* needed for all the objects to be picked at least once. A variation of the      *
* problem is to pick cards from a shuffled deck of 52 cards repeatedly and find  *
* out how many picks are needed before you see one of each suit. Assume a picked *
* card is placed back in the deck before picking another. Write a program to     *
* simulate the number of picks needed to get four cards from each suit and       *
* display the four cards picked (it is possible acard may be picked twice).      *
*********************************************************************************/
public class Exercise_07_24 {
public static void main(String[] args) {
        //initialize the deck
        String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"}; // cards[i] / values.length = suit
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"}; // cards[i] % values.length = value
        int[] cards = new int[52];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = i;
        }

        int numberOfPicks = 0;
        int[] pickedCards = new int[4];
        for (int i = 0; i < pickedCards.length; i++) {
            pickedCards[i] = -1;
        }


        //we need to pick four cards of different suit
        for (int i = 0; i < pickedCards.length; i++) {
            while (true) {
                int random = (int) (Math.random() * cards.length); // pick a random card
                numberOfPicks++;
                shuffle(cards); // shuffle all cards together with the picked
                if (isDifferentSuit(pickedCards, random)) {
                    //if it's different suit - remember it as picked
                    pickedCards[i] = random;
                    break;
                }//if not - continue picking
            }
            System.out.printf("%s of %s%n", values[pickedCards[i] % 13], suits[pickedCards[i] / 13]);
        }

        System.out.printf("Number of picks: %d%n", numberOfPicks);

    }

    private static boolean isDifferentSuit(int[] pickedCards, int card) {
        for (int i = 0; i < pickedCards.length; i++) {
            if (pickedCards[i] >= 0) {
                if (pickedCards[i] / 13 == card / 13)
                    return false;
            }
        }
        return true;
    }

    //strictly speaking we don't even need to shuffle because we pick a random card
    //but just for the sake of the game
    public static void shuffle(int[] cards) {
        for (int i = 0; i < cards.length / 2; i++) {
            int random = (int) (Math.random() * cards.length / 2);

            int temp = cards[random];
            cards[random] = cards[cards.length - 1 - random];
            cards[cards.length - 1 - random] = temp;
        }
    }
}
