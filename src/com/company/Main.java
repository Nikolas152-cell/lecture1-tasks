package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static int[] exampleArray = new int[100]; //task1
    Collection<Figure> figures = new ArrayList<Figure>(){{add(new Cube(1));add(new Sphere(3));//task2
    add(new Cilinder(22,40));add(new Cilinder(1,1));}};
    Collection<String> texts = new ArrayList<String>();
    public static void main(String[] args) {

    }
    //task1
    /*
    taking array from paramethres
    pulling from him positive number and inserting for a new array in 1 cycle
    then in 2 cycles we bubble sorting new array*/
    public static int[] positive(int[] array)
    {
       int lengthForNewArray = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i]>=0)
            {
                lengthForNewArray++;
            }
        }
        int[] newArrayForPositiveNumbers = new int[lengthForNewArray];
        for (int i = 0, variableForIndex = 0; i < array.length; i++) {
            if(array[i]>=0)
            {
                newArrayForPositiveNumbers[variableForIndex] = array[i];
                variableForIndex++;
            }
        }
        for (int i = 0; i < newArrayForPositiveNumbers.length; i++) {
            for (int j = 0; j < newArrayForPositiveNumbers.length-1; j++) {
                if (newArrayForPositiveNumbers[j]<newArrayForPositiveNumbers[j+1])
                {
                    int temp = newArrayForPositiveNumbers[j];
                    newArrayForPositiveNumbers[j] = newArrayForPositiveNumbers[j+1];
                    newArrayForPositiveNumbers[j+1] = temp;
                }
            }
        }
        return newArrayForPositiveNumbers;
    }

    public static void reverse(int[] input) {
        int last = input.length - 1;
        int middle = input.length / 2;
        for (int i = 0; i <= middle; i++)
        { int temp = input[i];
            input[i] = input[last - i];
            input[last - i] = temp;
            }
    }


    @Test
    public void testPositive()
    {
        exampleArray = new Random().ints(100, -100,100).toArray();
        IntStream intStream = Arrays.stream(exampleArray);

        int[] expectedArray = intStream.filter(element -> element>0).sorted().toArray();
        reverse(expectedArray);
        Assert.assertArrayEquals("Arrays are different",  expectedArray , positive(exampleArray));
    }


    //task2

    public static Map<String, Integer> popularHashtags(List<String> texts)
    {
        Stream<String> streamTexts = texts.stream();

        //create map for storing #hashtags and quantity of their appearences
        Map<String, Integer> mapForStoringHashtags = new TreeMap<>();
        List<String> topPopularHashTags = new ArrayList<>();
        for (Iterator iter = texts.listIterator(); iter.hasNext(); ) {
            String element = (String)iter.next();
            if(element.contains("#"))
            {
                mapForStoringHashtags.put(element, mapForStoringHashtags.merge(element,1, Integer::sum));
            }
            else {
                continue;
            }
        }
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        mapForStoringHashtags.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(5).
                forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));


        return reverseSortedMap;
    }

    @Test
    public void testPopularHashtags()
    {
        Stream<String> streamFromHashtags = texts.stream();
        //streamFromHashtags.

    }


    //task3
    public Collection<Figure> sortFigures(List<Figure> figures)
    {
        Collections.sort(figures, new Comparator<Figure>() {
            @Override
            public int compare(Figure o1, Figure o2) {
                return (int) (o1.volume()- o2.volume());
            }
        });
        return figures;
    }




    @Test
    public void testSortFigures()
    {
        Stream<Figure> streamFromList = figures.stream();
        for (Figure figure: figures) {
            System.out.println(figure.volume());
        }

        Assert.assertArrayEquals(streamFromList.sorted().toArray(), sortFigures((List<Figure>) figures).toArray());
        for (Figure figure: figures) {
            System.out.println(figure.volume());
        }
    }


}


