package csaba.airbnb.outils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import csaba.airbnb.logements.Appartement;
import csaba.airbnb.logements.Logement;
import csaba.airbnb.logements.Maison;
import csaba.airbnb.utilisateurs.Hote;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;

public class LogementsSaxParser {

    private static ArrayList<Logement> logements;
    private static ArrayList<Hote> hotes;

    public static void parse(String fileName, ArrayList<Logement> logements, ArrayList<Hote> hotes) throws ParserConfigurationException, SAXException, IOException {
        if (hotes == null || logements == null) {
            throw new IllegalArgumentException("listHotes == null || listLogements == null");
        }

        LogementsSaxParser.logements = logements;
        LogementsSaxParser.hotes = hotes;

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        LogementsSaxParserHandler saxParserHandler = new LogementsSaxParserHandler();
        saxParser.parse(fileName, saxParserHandler);
    }

    private static class LogementsSaxParserHandler extends DefaultHandler {

        private static final String APPARTEMENT = "Appartement";
        private static final String HOTE = "hote";
        private static final String NOM = "nom";
        private static final String AGE = "age";
        private static final String PRENOM = "prenom";
        private static final String DELAI = "delaiReponse";
        private static final String TARIF_PAR_NUIT = "tarifParNuit";
        private static final String ADRESSE = "adresse";
        private static final String SUPERFICIE = "superficie";
        private static final String NB_VOYAGEURS_MAX = "nbVoyageursMax";
        private static final String NUMERO_ETAGE = "numeroEtage";
        private static final String SUPERFICIE_BALCON = "superficieBalcon";
        private static final String MAISON = "Maison";
        private static final String SUPERFICIE_JARDIN = "superficieJardin";
        private static final String POSSEDE_PISCINE = "possedePiscine";

        private String name;
        private String nom;
        private String prenom;
        private String age;
        private String delai;
        private String tarifParNuit;
        private String adresse;
        private String superficie;
        private String nbVoyageursMax;
        private String numeroEtage;
        private String superficieBalcon;
        private String superficieJardin;
        private String possedePiscine;

        private Hote hote;

        private StringBuilder elementValue;

        @Override
        public void characters(char[] caracteres, int debut, int longueur) {
            elementValue.append(caracteres, debut, longueur);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {

            switch (qName) {
                case APPARTEMENT, MAISON -> name = attributes.getValue("name");
                default -> elementValue = new StringBuilder();
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            switch (qName) {
                case NOM -> nom = elementValue.toString();
                case PRENOM -> prenom = elementValue.toString();
                case AGE -> age = elementValue.toString();
                case DELAI -> delai = elementValue.toString();
                case TARIF_PAR_NUIT -> tarifParNuit = elementValue.toString();
                case ADRESSE -> adresse = elementValue.toString();
                case SUPERFICIE -> superficie = elementValue.toString();
                case NB_VOYAGEURS_MAX -> nbVoyageursMax = elementValue.toString();
                case NUMERO_ETAGE -> numeroEtage = elementValue.toString();
                case SUPERFICIE_BALCON -> superficieBalcon = elementValue.toString();
                case SUPERFICIE_JARDIN -> superficieJardin = elementValue.toString();
                case POSSEDE_PISCINE -> possedePiscine = elementValue.toString();
                case HOTE -> {
                    hote = new Hote(prenom, nom, Integer.parseInt(age), Integer.parseInt(delai));
                    boolean isPresent = false;
                    for (Hote h : hotes) {
                        if (h.equals(hote)) {
                            isPresent = true;
                            hote = h;
                            break;
                        }
                    }
                    if (!isPresent) {
                        hotes.add(hote);
                    }
                }

                // OU
                //int index = hotes.indexOf(hote);
                //if (index != -1) {
                //    hote = hotes.get(index);
                //} else {
                //    hotes.add(hote);
                //}
                case APPARTEMENT -> {
                    Appartement appartement = new Appartement(name, hote, Integer.parseInt(tarifParNuit), adresse, Integer.parseInt(superficie), Integer.parseInt(nbVoyageursMax), Integer.parseInt(numeroEtage), Integer.parseInt(superficieBalcon));
                    logements.add(appartement);
                }
                case MAISON -> {
                    Maison maison = new Maison(name, hote, Integer.parseInt(tarifParNuit), adresse, Integer.parseInt(superficie), Integer.parseInt(nbVoyageursMax), Integer.parseInt(superficieJardin), possedePiscine.equals("1"));
                    logements.add(maison);
                }
            }
        }
    }

}