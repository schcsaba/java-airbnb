package csaba.airbnb.logements;

import csaba.airbnb.outils.AirBnBData;

import java.util.ArrayList;
import java.util.function.Predicate;
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
//            if (logement.getNbVoyageursMax() < nbVoyageurs) {
//                continue;
//            }
//            if (logement.getTarifParNuit() < tarifMinParNuit || logement.getTarifParNuit() > tarifMaxParNuit)
//                continue;
//            if (possedePiscine == YES) {
//                if (logement instanceof Maison maison) {
//                    if (!maison.isPossedePiscine())
//                        continue;
//                } else
//                    continue;
//
//            } else if (possedePiscine == NO) {
//                if (logement instanceof Maison maison) {
//                    if (maison.isPossedePiscine())
//                        continue;
//                }
//            }
//            if (possedeBalcon == YES) {
//                if (logement instanceof Appartement appartement) {
//                    if (appartement.getSuperficieBalcon() == 0)
//                        continue;
//                } else
//                    continue;
//            } else if (possedeBalcon == NO) {
//                if (logement instanceof Appartement appartement) {
//                    if (appartement.getSuperficieBalcon() != 0)
//                        continue;
//                }
//            }
//            result.add(logement);
//        }

        result = airBnBData
                .stream()
                .filter(
                        predicateNbVoyageurs()
                                .and(
                                        predicateTarifMin()
                                )
                                .and(
                                        predicateTarifMax()
                                )
                                .and(
                                        predicateBalcon()
                                )
                                .and(
                                        predicatePiscine()
                                )
                                .and(
                                        predicateJardin()
                                )
                )
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }

    public Predicate<Logement> predicateNbVoyageurs() {
        return logement -> logement.getNbVoyageursMax() >= nbVoyageurs;
    }

    public Predicate<Logement> predicateTarifMin() {
        return logement -> logement.getTarifParNuit() >= tarifMinParNuit;
    }

    public Predicate<Logement> predicateTarifMax() {
        return logement -> logement.getTarifParNuit() <= tarifMaxParNuit;
    }

    public Predicate<Logement> predicateBalcon() {
        if (possedeBalcon == YES) {
            return logement -> logement instanceof Appartement && ((Appartement) logement).getSuperficieBalcon() > 0;
        } else if (possedeBalcon == NO) {
            return logement -> !(logement instanceof Appartement) || ((Appartement) logement).getSuperficieBalcon() == 0;
        } else {
            return logement -> true;
        }
    }

    public Predicate<Logement> predicatePiscine() {
        if (possedePiscine == YES) {
            return logement -> logement instanceof Maison && ((Maison) logement).isPossedePiscine();
        } else if (possedePiscine == NO) {
            return logement -> !(logement instanceof Maison) || !((Maison) logement).isPossedePiscine();
        } else {
            return logement -> true;
        }
    }

    public Predicate<Logement> predicateJardin() {
        if (possedeJardin == YES) {
            return logement -> logement instanceof Maison && (((Maison) logement).getSuperficieJardin() > 0);
        } else if (possedeJardin == NO) {
            return logement -> !(logement instanceof Maison) || !(((Maison) logement).getSuperficieJardin() > 0);
        } else {
            return logement -> true;
        }
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
