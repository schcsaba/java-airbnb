package csaba.airbnb.logements;

import csaba.airbnb.outils.AirBnBData;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Search {
    private static final int NOT_SET = 0;
    private static final int YES = 1;
    private static final int NO = -1;
    private final int nbVoyageurs;
    private final int tarifMinParNuit;
    private final int tarifMaxParNuit;
    private final int possedePiscine;
    private final int possedeJardin;
    private final int possedeBalcon;

    private Search(Builder builder) {
        this.nbVoyageurs = builder.nbVoyageurs;
        this.tarifMinParNuit = builder.tarifMinParNuit;
        this.tarifMaxParNuit = builder.tarifMaxParNuit;
        this.possedePiscine = builder.possedePiscine;
        this.possedeJardin = builder.possedeJardin;
        this.possedeBalcon = builder.possedeBalcon;
    }

    public ArrayList<Logement> result() {
        ArrayList<Logement> airBnBData = AirBnBData.getInstance().getListLogements();
        ArrayList<Logement> result;
//        ArrayList<Logement> result = new ArrayList<>();
//        for (Logement logement : airBnBData) {
//            if (possedeBalcon != NOT_SET) {
//                if (logement.getClass() != Appartement.class)
//                    continue;
//                Appartement appartement = (Appartement) logement;
//                int appartementPossedeBalcon = appartement.getSuperficieBalcon() > 0 ? YES : NO;
//                if (possedeBalcon != appartementPossedeBalcon) {
//                    continue;
//                }
//            } else if (possedeJardin != NOT_SET || possedePiscine != NOT_SET) {
//                if (logement.getClass() != Maison.class)
//                    continue;
//                Maison maison = (Maison) logement;
//                int maisonPossedeJardin = maison.getSuperficieJardin() > 0 ? YES : NO;
//                int maisonPossedePiscine = maison.isPossedePiscine() ? YES : NO;
//                if (possedePiscine != maisonPossedePiscine) {
//                    continue;
//                } else if (possedeJardin != maisonPossedeJardin) {
//                    continue;
//                }
//            } else {
//                if (logement.getNbVoyageursMax() < nbVoyageurs)
//                    continue;
//                if (logement.getTarifParNuit() > tarifMaxParNuit)
//                    continue;
//                if (logement.getTarifParNuit() < tarifMinParNuit)
//                    continue;
//            }
//            result.add(logement);
//        }
        if (possedeBalcon != NOT_SET) {
            result = airBnBData
                    .stream()
                    .filter(logement -> logement instanceof Appartement)
                    .map(logement -> (Appartement) logement)
                    .filter(logement -> logement.getNbVoyageursMax() >= nbVoyageurs)
                    .filter(logement -> logement.getTarifParNuit() >= tarifMinParNuit)
                    .filter(logement -> logement.getTarifParNuit() <= tarifMaxParNuit)
                    .filter(logement -> (logement.getSuperficieBalcon() > 0 ? YES : NO) == possedeBalcon)
                    .collect(Collectors.toCollection(ArrayList::new));

        } else if (possedePiscine != NOT_SET || possedeJardin != NOT_SET) {
            result = airBnBData
                    .stream()
                    .filter(logement -> logement instanceof Maison)
                    .map(logement -> (Maison) logement)
                    .filter(logement -> logement.getNbVoyageursMax() >= nbVoyageurs)
                    .filter(logement -> logement.getTarifParNuit() >= tarifMinParNuit)
                    .filter(logement -> logement.getTarifParNuit() <= tarifMaxParNuit)
                    .filter(logement -> possedePiscine == 0 || ((logement.isPossedePiscine() ? YES : NO) == possedePiscine))
                    .filter(logement -> possedeJardin == 0 || ((logement.getSuperficieJardin() > 0 ? YES : NOT_SET) == possedeJardin))
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            result = airBnBData
                    .stream()
                    .filter(logement -> logement.getNbVoyageursMax() >= nbVoyageurs)
                    .filter(logement -> logement.getTarifParNuit() >= tarifMinParNuit)
                    .filter(logement -> logement.getTarifParNuit() <= tarifMaxParNuit)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return result;
    }

    public static class Builder {
        private final int nbVoyageurs;
        private int tarifMinParNuit;
        private int tarifMaxParNuit;
        private int possedePiscine;
        private int possedeJardin;
        private int possedeBalcon;

        public Builder(int nbVoyageurs) {
            this.nbVoyageurs = nbVoyageurs;
            tarifMinParNuit = 0;
            tarifMaxParNuit = Integer.MAX_VALUE;
            possedePiscine = NOT_SET;
            possedeJardin = NOT_SET;
            possedeBalcon = NOT_SET;
        }

        public Builder tarifMinParNuit(int tarifMinParNuit) {
            this.tarifMinParNuit = tarifMinParNuit;
            return this;
        }

        public Builder tarifMaxParNuit(int tarifMaxParNuit) {
            this.tarifMaxParNuit = tarifMaxParNuit;
            return this;
        }

        public Builder possedePiscine(boolean possedePiscine) {
            this.possedePiscine = possedePiscine ? YES : NO;
            return this;
        }

        public Builder possedeJardin(boolean possedeJardin) {
            this.possedeJardin = possedeJardin ? YES : NO;
            return this;
        }

        public Builder possedeBalcon(boolean possedeBalcon) {
            this.possedeBalcon = possedeBalcon ? YES : NO;
            return this;
        }

        public Search build() {
            return new Search(this);
        }
    }
}
