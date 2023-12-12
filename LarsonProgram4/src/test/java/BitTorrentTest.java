import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BitTorrentTest {
    @Test
    void testTorrentPieces1() throws FileNotFoundException {
        BitTorrent evaluator = new BitTorrent("onePhrase.txt", 5);
        evaluator.torrentPieces();
        assertEquals("259921423: 2: jumps over\n" +
                "1794146334: 1: the quick red fox\n" +
                "1664561561: 3: the lazy brown dog.\n", evaluator.printTorrent());
    }
    @Test
    void testTorrentPieces2() throws FileNotFoundException {
        BitTorrent evaluator = new BitTorrent("twoPhrases.txt", 15);
        evaluator.torrentPieces();
        assertEquals("259921423: 2: jumps over\n" +
                "-1941036129: 2: isn't gold.\n" +
                "-414570365: 1: All the glitter\n" +
                "1794146334: 1: the quick red fox\n" +
                "1664561561: 3: the lazy brown dog.\n", evaluator.printTorrent());
    }
    @Test
    void testTorrentPieces3() throws FileNotFoundException {
        BitTorrent evaluator = new BitTorrent("DavidBowie.txt", 15);
        evaluator.torrentPieces();
        assertEquals("1640745390: 4: It's on America's tortured brow That Mickey Mouse has grown up a cow Now the workers have struck for fame 'Cause Lennon's on sale again See the mice in their million hordes From Ibiza to the Norfolk Broads Rule Britannia is out of bounds To my mother, my dog, and clowns But the film is a saddening bore 'Cause I wrote it ten times or more It's about to be writ again As I ask you to focus on\n" +
                "-304554883: 5: Sailors fighting in the dance hall Oh man, look at those cavemen go It's the freakiest show Take a look at the lawman Beating up the wrong guy Oh man, wonder if he'll ever know He's in the best selling show Is there life on Mars?\n" +
                "203408186: 5: We can be Heroes We can be Heroes We can be Heroes Just for one day We can be Heroes We're nothing, and nothing will help us Maybe we're lying, then you better not stay But we could be safer, just for one day Oh-oh-oh-ohh, oh-oh-oh-ohh, just for one day\n" +
                "-607009417: 2: I, I wish you could swim Like the dolphins, like dolphins can swim Though nothing, nothing will keep us together We can beat them, for ever and ever Oh we can be Heroes, just for one day\n" +
                "-128742891: 4: I, I can remember (I remember) Standing, by the wall (by the wall) And the guns, shot above our heads (over our heads) And we kissed, as though nothing could fall (nothing could fall) And the shame, was on the other side Oh we can beat them, for ever and ever Then we could be Heroes, just for one day\n" +
                "-1927673261: 1: It's a God-awful small affair To the girl with the mousy hair But her mummy is yelling, \"No\" And her daddy has told her to go But her friend is nowhere to be seen Now she walks through her sunken dream To the seat with the clearest view And she's hooked to the silver screen\n" +
                "33407606: 3: I, I will be king And you, you will be queen Though nothing will drive them away We can be Heroes, just for one dayWe can be us, just for one day\n" +
                "-1152798046: 3: Let's dance Put on your red shoes and dance the blues Let's sway Under the moonlight, this serious moonlight Let's dance Let's dance Let's dance, dance, dance\n" +
                "-1380735874: 2: But the film is a saddening bore For she's lived it ten times or more She could spit in the eyes of fools As they ask her to focus on Sailors fighting in the dance hall Oh man, look at those cavemen go It's the freakiest show Take a look at the lawman Beating up the wrong guy\n" +
                "1866827955: 3: Oh man, wonder if he'll ever know He's in the best selling show Is there life on Mars?\n" +
                "758242152: 1: Let's dance Put on your red shoes and dance the blues Let's dance To the song they're playin' on the radio Let's sway While color lights up your face Let's sway Sway through the crowd to an empty space If you say run I'll run with you And if you say hide We'll hide Because my love for you Would break my heart in two If you should fall into my arms And tremble like a flower\n" +
                "-1721051824: 2: Let's dance Let's dance For fear your grace should fall Let's dance For fear tonight is all Let's sway You could look into my eyes Let's sway Under the moonlight, this serious moonlight And if you say run I'll run with you And if you say hide We'll hide Because my love for you Would break my heart in two If you should fall into my arms And tremble like a flower\n" +
                "-40142618: 1: I, I will be king And you, you will be queen Though nothing will drive them away We can beat them, just for one day We can be heroes, just for one day And you, you can be mean And I, I'll drink all the time 'Cause we're lovers, and that is a fact Yes we're lovers, and that is that Though nothing will keep us together We could steal time just for one day We can be heroes for ever and ever What d'you say?\n", evaluator.printTorrent());
    }
    @Test
    void testStitch1() throws FileNotFoundException {
        BitTorrent evaluator = new BitTorrent("onePhrase.txt", 5);
        evaluator.torrentPieces();
        ArrayList<Integer> hash = new ArrayList<>();
        hash.add(1794146334);
        hash.add(259921423);
        hash.add(1664561561);
        assertEquals("the quick red fox\n" +
                "jumps over\n" +
                "the lazy brown dog.\n", evaluator.stitch(hash));
    }
    @Test
    void testStitch2() throws FileNotFoundException {
        BitTorrent evaluator = new BitTorrent("twoPhrases.txt", 15);
        evaluator.torrentPieces();
        ArrayList<Integer> hash = new ArrayList<>();
        hash.add(-1941036129);
        hash.add(-414570365);
        assertEquals("All the glitter\n" +
                "isn't gold.\n", evaluator.stitch(hash));
    }

    @Test
    void testStitch4() throws FileNotFoundException {
        BitTorrent evaluator = new BitTorrent("DavidBowie.txt", 15);
        evaluator.torrentPieces();
        ArrayList<Integer> hash = new ArrayList<>();
        hash.add(-1152798046);
        hash.add(758242152);
        hash.add(-1721051824);

        assertEquals("Let's dance Put on your red shoes and dance the blues Let's dance To the song they're playin' on the radio Let's sway While color lights up your face Let's sway Sway through the crowd to an empty space If you say run I'll run with you And if you say hide We'll hide Because my love for you Would break my heart in two If you should fall into my arms And tremble like a flower\n" +
                "Let's dance Let's dance For fear your grace should fall Let's dance For fear tonight is all Let's sway You could look into my eyes Let's sway Under the moonlight, this serious moonlight And if you say run I'll run with you And if you say hide We'll hide Because my love for you Would break my heart in two If you should fall into my arms And tremble like a flower\n" +
                "Let's dance Put on your red shoes and dance the blues Let's sway Under the moonlight, this serious moonlight Let's dance Let's dance Let's dance, dance, dance\n", evaluator.stitch(hash));
    }

    @Test
    void testStitch6() throws FileNotFoundException {
        BitTorrent evaluator = new BitTorrent("DavidBowie.txt", 15);
        evaluator.torrentPieces();
        ArrayList<Integer> hash = new ArrayList<>();
        hash.add(203408186);
        hash.add(-607009417);
        hash.add(-128742891);
        hash.add(-40142618);
        hash.add(33407606);
        assertEquals("I, I will be king And you, you will be queen Though nothing will drive them away We can beat them, just for one day We can be heroes, just for one day And you, you can be mean And I, I'll drink all the time 'Cause we're lovers, and that is a fact Yes we're lovers, and that is that Though nothing will keep us together We could steal time just for one day We can be heroes for ever and ever What d'you say?\n" +
                "I, I wish you could swim Like the dolphins, like dolphins can swim Though nothing, nothing will keep us together We can beat them, for ever and ever Oh we can be Heroes, just for one day\n" +
                "I, I will be king And you, you will be queen Though nothing will drive them away We can be Heroes, just for one dayWe can be us, just for one day\n" +
                "I, I can remember (I remember) Standing, by the wall (by the wall) And the guns, shot above our heads (over our heads) And we kissed, as though nothing could fall (nothing could fall) And the shame, was on the other side Oh we can beat them, for ever and ever Then we could be Heroes, just for one day\n" +
                "We can be Heroes We can be Heroes We can be Heroes Just for one day We can be Heroes We're nothing, and nothing will help us Maybe we're lying, then you better not stay But we could be safer, just for one day Oh-oh-oh-ohh, oh-oh-oh-ohh, just for one day\n", evaluator.stitch(hash));
    }

    @Test
    void testGetPieces2() throws FileNotFoundException {
        BitTorrent evaluator = new BitTorrent("onePhrase.txt", 5);
        evaluator.torrentPieces();
        evaluator.tracker(5);
        assertEquals("1794146334: the quick red fox\n" +
                "1\n" +
                "0\n" +
                "4\n" +
                "2\n" +
                "3\n" +
                "259921423: jumps over\n" +
                "4\n" +
                "0\n" +
                "2\n" +
                "3\n" +
                "1\n" +
                "1664561561: the lazy brown dog.\n" +
                "3\n" +
                "2\n" +
                "4\n" +
                "0\n" +
                "1\n", evaluator.printSwarm());
    }
    @Test
    void testGetPieces3() throws FileNotFoundException {
        BitTorrent evaluator = new BitTorrent("twoPhrases.txt", 15);
        evaluator.torrentPieces();
        evaluator.tracker(4);
        assertEquals("1794146334: the quick red fox\n" +
                "6\n" +
                "0\n" +
                "4\n" +
                "11\n" +
                "259921423: jumps over\n" +
                "7\n" +
                "3\n" +
                "4\n" +
                "0\n" +
                "1664561561: the lazy brown dog.\n" +
                "7\n" +
                "3\n" +
                "13\n" +
                "0\n" +
                "-414570365: All the glitter\n" +
                "10\n" +
                "6\n" +
                "3\n" +
                "7\n" +
                "-1941036129: isn't gold.\n" +
                "3\n" +
                "13\n" +
                "2\n" +
                "12\n", evaluator.printSwarm());
    }
    @Test
    void testGetPieces4() throws FileNotFoundException {
        BitTorrent evaluator = new BitTorrent("DavidBowie.txt", 17);
        evaluator.torrentPieces();
        evaluator.tracker(3);
        assertEquals("-1927673261: It's a God-awful small affair To the girl with the mousy hair But her mummy is yelling, \"No\" And her daddy has told her to go But her friend is nowhere to be seen Now she walks through her sunken dream To the seat with the clearest view And she's hooked to the silver screen\n" +
                "3\n" +
                "12\n" +
                "1\n" +
                "-1380735874: But the film is a saddening bore For she's lived it ten times or more She could spit in the eyes of fools As they ask her to focus on Sailors fighting in the dance hall Oh man, look at those cavemen go It's the freakiest show Take a look at the lawman Beating up the wrong guy\n" +
                "6\n" +
                "16\n" +
                "10\n" +
                "1866827955: Oh man, wonder if he'll ever know He's in the best selling show Is there life on Mars?\n" +
                "2\n" +
                "14\n" +
                "12\n" +
                "1640745390: It's on America's tortured brow That Mickey Mouse has grown up a cow Now the workers have struck for fame 'Cause Lennon's on sale again See the mice in their million hordes From Ibiza to the Norfolk Broads Rule Britannia is out of bounds To my mother, my dog, and clowns But the film is a saddening bore 'Cause I wrote it ten times or more It's about to be writ again As I ask you to focus on\n" +
                "15\n" +
                "9\n" +
                "14\n" +
                "-304554883: Sailors fighting in the dance hall Oh man, look at those cavemen go It's the freakiest show Take a look at the lawman Beating up the wrong guy Oh man, wonder if he'll ever know He's in the best selling show Is there life on Mars?\n" +
                "6\n" +
                "7\n" +
                "16\n" +
                "-40142618: I, I will be king And you, you will be queen Though nothing will drive them away We can beat them, just for one day We can be heroes, just for one day And you, you can be mean And I, I'll drink all the time 'Cause we're lovers, and that is a fact Yes we're lovers, and that is that Though nothing will keep us together We could steal time just for one day We can be heroes for ever and ever What d'you say?\n" +
                "6\n" +
                "0\n" +
                "10\n" +
                "-607009417: I, I wish you could swim Like the dolphins, like dolphins can swim Though nothing, nothing will keep us together We can beat them, for ever and ever Oh we can be Heroes, just for one day\n" +
                "16\n" +
                "6\n" +
                "3\n" +
                "33407606: I, I will be king And you, you will be queen Though nothing will drive them away We can be Heroes, just for one dayWe can be us, just for one day\n" +
                "0\n" +
                "15\n" +
                "14\n" +
                "-128742891: I, I can remember (I remember) Standing, by the wall (by the wall) And the guns, shot above our heads (over our heads) And we kissed, as though nothing could fall (nothing could fall) And the shame, was on the other side Oh we can beat them, for ever and ever Then we could be Heroes, just for one day\n" +
                "11\n" +
                "14\n" +
                "16\n" +
                "203408186: We can be Heroes We can be Heroes We can be Heroes Just for one day We can be Heroes We're nothing, and nothing will help us Maybe we're lying, then you better not stay But we could be safer, just for one day Oh-oh-oh-ohh, oh-oh-oh-ohh, just for one day\n" +
                "13\n" +
                "2\n" +
                "6\n" +
                "758242152: Let's dance Put on your red shoes and dance the blues Let's dance To the song they're playin' on the radio Let's sway While color lights up your face Let's sway Sway through the crowd to an empty space If you say run I'll run with you And if you say hide We'll hide Because my love for you Would break my heart in two If you should fall into my arms And tremble like a flower\n" +
                "2\n" +
                "6\n" +
                "10\n" +
                "-1721051824: Let's dance Let's dance For fear your grace should fall Let's dance For fear tonight is all Let's sway You could look into my eyes Let's sway Under the moonlight, this serious moonlight And if you say run I'll run with you And if you say hide We'll hide Because my love for you Would break my heart in two If you should fall into my arms And tremble like a flower\n" +
                "2\n" +
                "1\n" +
                "9\n" +
                "-1152798046: Let's dance Put on your red shoes and dance the blues Let's sway Under the moonlight, this serious moonlight Let's dance Let's dance Let's dance, dance, dance\n" +
                "9\n" +
                "1\n" +
                "13\n", evaluator.printSwarm());
    }

}