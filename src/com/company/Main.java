package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {250, 260, 270, 200, 300, 240, 280, 230};
    public static int[] heroesDamage = {20, 15, 25, 0, 10, 25, 15, 20};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Thor", "Berserk", "Lucky"};
    public static int roundNumber = 0;


    public static void main(String[] args) {
        System.out.println("Game start");
        printStatistics();
        while (!isGameFinished()) {
            roundNumber++;
            System.out.println("Round " + roundNumber);
            round();
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose " + bossDefenceType);
    }


    public static void round() {
        changeBossDefence();
        heroesHit();
        if (bossHealth > 0) {
            bossHits();
        }
        golem();
        thor();
        berserk();
        medicGivesTreat();
        printStatistics();

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }


    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] < bossDamage) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (bossHealth < heroesDamage[i]) {
                    bossHealth = 0;
                }
                if (bossHealth > 0) {
                    if (bossDefenceType == heroesAttackType[i]) {
                        Random r = new Random();
                        int coef = r.nextInt(10);
                        if (bossHealth - heroesDamage[i] < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i] * coef;
                        }
                        System.out.println("Critical attack: " + (heroesDamage[i] * coef));
                    } else {
                        if (bossHealth - heroesDamage[i] < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i];
                        }
                    }
                } else {
                    break;
                }
            }
        }
    }

    public static void golem() {
        int damage1 = bossDamage / 5;
        int damage2 = bossDamage - damage1;
        for (int i = 0; i < heroesHealth.length; i++)
            if (heroesHealth[4] < bossDamage) {
                heroesHealth[4] = 0;
                heroesHealth[i] = heroesHealth[i] - damage2;
                heroesHealth[4] = heroesHealth[4] - damage1;
            }
    }


    public static void thor() {
        boolean skipRound = false;
        if (heroesHealth[6] > 0) {
            Random t = new Random();
            int deaf = t.nextInt(1) + 1;
            if (heroesHealth[6] > 0) {
                if (deaf == 1) {
                    skipRound = true;
                } else skipRound = false;
            }
        }
    }

    public static void berserk(){
        Random a = new Random();
        int block = a.nextInt(49) +1;
        if (heroesHealth[7] > 0) {
            heroesHealth[7] += block;
            bossHealth -= (heroesDamage[7] + block);
        }
    }



    public static void lucky() {
        if (heroesHealth[8] > 0) {
            Random i = new Random();
            int chance = i.nextInt(1)+1 ;
            if (chance ==1) {
                heroesHealth[8] = heroesHealth[8] + bossDamage;
            }
        }
    }


    public static void medicGivesTreat() {
        Random r = new Random();
        int chooseHero = r.nextInt(heroesAttackType.length);
        if (heroesHealth.length < 100) {
            if (heroesHealth.length > 0) {
                if (heroesHealth[3] > 0) {
                    int healthCount = r.nextInt(30) + 20;
                    heroesHealth[chooseHero] = heroesHealth[chooseHero] + healthCount;
                    System.out.println("Medic add health to " + chooseHero + '-' + healthCount);
                }
            }
        }
    }


    public static void printStatistics() {
        System.out.println("--------------------");
        System.out.println("Boss Health: " + bossHealth);
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " Health: " + heroesHealth[i]);
        }
        System.out.println("--------------------");
    }
}
