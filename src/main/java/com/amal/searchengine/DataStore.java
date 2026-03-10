package com.amal.searchengine;

import java.io.*;
import java.util.List;

public class DataStore {

    private static final String PAGES_FILE = "pages.ser";
    private static final String INDEX_FILE = "index.ser";

    public static void savePages(List<WebPage> pages) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PAGES_FILE))) {
            out.writeObject(pages);
            System.out.println("Pages saved to disk.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	public static List<WebPage> loadPages() {
        File file = new File(PAGES_FILE);

        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            System.out.println("Pages loaded from disk.");
            return (List<WebPage>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveIndex(InvertedIndex index) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(INDEX_FILE))) {
            out.writeObject(index);
            System.out.println("Index saved to disk.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InvertedIndex loadIndex() {
        File file = new File(INDEX_FILE);

        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            System.out.println("Index loaded from disk.");
            return (InvertedIndex) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}