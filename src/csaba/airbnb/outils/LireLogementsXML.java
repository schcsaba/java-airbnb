package csaba.airbnb.outils;

import csaba.airbnb.logements.Logement;
import csaba.airbnb.utilisateurs.Hote;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;

public class LireLogementsXML {

    private static ArrayList<Logement> logements;
    private static ArrayList<Hote> hotes;

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                boolean nom = false;
                String vNom;
                boolean prenom = false;
                String vPrenom;
                boolean age = false;
                int vAge;
                boolean delaiReponse = false;
                int vDelaiReponse;
                Hote hote;
                boolean tarifParNuit = false;
                int vTarifParNuit;
                boolean adresse = false;
                String vAdresse;
                boolean superficie = false;
                int vSuperficie;
                boolean nbVoyageursMax = false;
                int vNbVoyageursMax;
                boolean numeroEtage = false;
                int vNumeroEtage;
                boolean superficieBalcon = false;
                int vSuperficieBalcon;
                boolean superficieJardin = false;
                int vSuperficieJardin;
                boolean possedePiscine = false;
                boolean vPossedePiscine;

                //parser starts parsing a specific element inside the document
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    System.out.println("Start Element: " + qName);
                    if (qName.equals("nom")) {
                        nom = true;
                    }
                    if (qName.equals("prenom")) {
                        prenom = true;
                    }
                    if (qName.equals("age")) {
                        age = true;
                    }
                    if (qName.equals("delaiReponse")) {
                        delaiReponse = true;
                    }
                    if (qName.equals("tarifParNuit")) {
                        tarifParNuit = true;
                    }
                    if (qName.equals("adresse")) {
                        adresse = true;
                    }
                    if (qName.equals("superficie")) {
                        superficie = true;
                    }
                    if (qName.equals("nbVoyageursMax")) {
                        nbVoyageursMax = true;
                    }
                    if (qName.equals("numeroEtage")) {
                        numeroEtage = true;
                    }
                    if (qName.equals("superficieBalcon")) {
                        superficieBalcon = true;
                    }
                    if (qName.equals("superficieJardin")) {
                        superficieJardin = true;
                    }
                    if (qName.equals("possedePiscine")) {
                        possedePiscine = true;
                    }
                }

                //parser ends parsing the specific element inside the document
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    System.out.println("End Element: " + qName);
                    if (qName.equals("hote")) {
                        hote = new Hote(vPrenom, vNom, vAge, vDelaiReponse);
                        if (!hotes.contains(hote)) {
                            hotes.add(hote);
                        }
                    }
                }

                //reads the text value of the currently parsed element
                public void characters(char[] ch, int start, int length) throws SAXException {
                    String vStr = new String(ch, start, length);
                    int vInt;
                    try {
                        vInt = Integer.parseInt(new String(ch, start, length));
                    } catch (NumberFormatException e) {
                        vInt = 0;
                    }
                    if (nom) {
                        vNom = vStr;
                        System.out.println("nom : " + vNom);
                        nom = false;
                    }
                    if (prenom) {
                        vPrenom = vStr;
                        System.out.println("prenom : " + vPrenom);
                        prenom = false;
                    }
                    if (age) {
                        vAge = vInt;
                        System.out.println("age : " + vAge);
                        age = false;
                    }
                    if (delaiReponse) {
                        vDelaiReponse = vInt;
                        System.out.println("delaiReponse : " + vDelaiReponse);
                        delaiReponse = false;
                    }
                    if (tarifParNuit) {
                        vTarifParNuit = vInt;
                        System.out.println("tarifParNuit : " + vTarifParNuit);
                        tarifParNuit = false;
                    }
                    if (adresse) {
                        vAdresse = vStr;
                        System.out.println("adresse : " + vAdresse);
                        adresse = false;
                    }
                    if (superficie) {
                        vSuperficie = vInt;
                        System.out.println("superficie : " + vInt);
                        superficie = false;
                    }
                    if (nbVoyageursMax) {
                        vNbVoyageursMax = vInt;
                        System.out.println("nbVoyageursMax : " + vNbVoyageursMax);
                        nbVoyageursMax = false;
                    }
                    if (numeroEtage) {
                        vNumeroEtage = vInt;
                        System.out.println("numeroEtage : " + vNumeroEtage);
                        numeroEtage = false;
                    }
                    if (superficieBalcon) {
                        vSuperficieBalcon = vInt;
                        System.out.println("superficieBalcon : " + vSuperficieBalcon);
                        superficieBalcon = false;
                    }
                    if (superficieJardin) {
                        vSuperficieJardin = vInt;
                        System.out.println("superficieJardin : " + vSuperficieJardin);
                        superficieJardin = false;
                    }
                    if (possedePiscine) {
                        vPossedePiscine = Boolean.parseBoolean(new String(ch, start, length));
                        System.out.println("possedePiscine : " + vSuperficieJardin);
                        possedePiscine = false;
                    }
                }
            };
            saxParser.parse("logements.xml", handler);
            listerHotes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void listerHotes() {
        System.out.println("-------------------------------------");
        System.out.println("Liste des hôtes ");
        System.out.println();

        for (int i = 0; i < hotes.size(); i++) {
            System.out.print(i + 1 + " : ");
            hotes.get(i).afficher();
            System.out.println();
        }
        System.out.println();
    }
}
