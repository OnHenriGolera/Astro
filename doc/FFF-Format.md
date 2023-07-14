---
title: "FFF Format documentation - Français"
date: 2023-07-14
draft: false
author: "Robin VAN DE MERGHEL"
description: "Documentation du format FFF"
lang: "fr"
geometry: "margin=1.3in"
colorlinks: true
header-includes:
    - \hypersetup{colorlinks=false,
              allbordercolors={0 0 0},
              pdfborderstyle={/S/U/W 1}}
---

# Introduction

Pour intéragir avec différentes applications telles que [BellePoule](http://betton.escrime.free.fr/fencing-tournament-software/) ou encore [Engarde Escrime](https://engarde-service.com), le standard `FFF` a été créé. Il permet de décrire une compétition d'escrime et ses participants.

Ce standard est utilisé par la FIE (Fédération Internationale d'Escrime) et par la FFE (Fédération Française d'Escrime), et est donc utilisé par la majorité des logiciels d'escrime.

# Format

Le format `FFF` est un format de fichier texte, dont l'extension est `.fff`. Il est composé de 4 types de fichiers :

- Un classement de la FIE
- Un classement d'une fédération nationale
- Les résultats d'une compétition (juste le classement)
- Une sauvegarde de compétition en cours

## Séparateurs

Le format se rapprochant du format `CSV`, les séparateurs utilisés sont la virgule `,` et le point-virgule `;`. 

## Informations générales

### Première ligne

Au début du fichier, on retrouve deux lignes d'informations générales. La première ligne contient les champs suivants, séparés par des `;` :

| Numéro | Type    | Contenu   | Explication                                |
|--------|---------|-----------|--------------------------------------------|
| 11     | symbole | `FFF`     | Pour indiquer le format FFF                |
| 12     | symbole | System    | Jeu de caractères utilisé : `WIN` ou `DOS` |
| 13     | symbole | Type      | *détaillé ci-après*                        |
| 14     | chaine  | Organisme | L'organisme auteur du fichier              |

`Type` (information 13) peut prendre les valeurs suivantes :

| Valeur        | Explication                                                |
|---------------|------------------------------------------------------------|
| `CLASSEMENT`  | Pour un classement                                         |
| `COMPETITION` | Pour les résultats d'une compétition (juste le classement) |
| `SAUVEGARDE`  | Pour une sauvegarde de compétition en cours                |

On obtient une chaîne du type :

```
11;12;13;14;
```


#### Exemple

La première ligne d'un classement FIE est :

```
FFF;WIN;CLASSEMENT;FIE
```

C'est à dire les informations suivantes :

| Numéro | Type    | Contenu      |
|--------|---------|--------------|
| 11     | symbole | `FFF`        |
| 12     | symbole | `WIN`        |
| 13     | symbole | `CLASSEMENT` |
| 14     | chaine  | `FIE`        |



### Deuxième ligne

La deuxième ligne contient des métadonnées, séparées par des `;` :

| Numéro | Type    | Contenu    | Explication                                |
|--------|---------|------------|--------------------------------------------|
| 21     | date    | Date       | La date du classement ou de la compétition |
| 22     | symbole | Arme       | `fleuret`, `epee` ou `sabre`               |
| 23     | symbole | Sexe       | `m` ou `f`                                 |
| 24     | symbole | Categorie  | `senior`, `junior`, `cadet`...             |
| 25     | chaine  | Nom        | Le nom du classement ou de la compétition  |
| 26     | chaine  | Nom réduit | Le nom réduit                              |

On obtient une chaîne du type :

```
21;22;23;24;25;26;
```

#### Exemple

La deuxième ligne d'un classement FIE est :

```
7/6/2000;sabre;M;senior;Classement officiel de la FIE
```

C'est à dire les informations suivantes :

| Numéro | Type    | Contenu                         |
|--------|---------|---------------------------------|
| 21     | date    | `7/6/2000`                      |
| 22     | symbole | `sabre`                         |
| 23     | symbole | `M`                             |
| 24     | symbole | `senior`                        |
| 25     | chaine  | `Classement officiel de la FIE` |


## Informations sur les tireurs

Toutes les lignes qui suivent contiennent les informations sur les tireurs. Chaque ligne est composée de 4 parties séparées par des `;` :

1. Les informations générales
2. Les informations de la FIE
3. Les informations de la fédération nationale
4. Les informations d'une compétition

### Informations générales

Les champs suivants sont séparés par des `,` :

| Numéro | Type    | Contenu           | Explication                    |
|--------|---------|-------------------|--------------------------------|
| G1     | chaine  | Nom               | Le nom du tireur               |
| G2     | chaine  | Prénom            | Le prénom du tireur            |
| G3     | date    | Date de naissance | La date de naissance du tireur |
| G4     | symbole | Sexe              | `m` ou `f`                     |
| G5     | symbole | Nation            | La nation du tireur            |

### Informations de la FIE

Les champs suivants sont séparés par des `,` :

| Numéro | Type    | Contenu     | Explication                       |
|--------|---------|-------------|-----------------------------------|
| I1     | chaine  | Licence FIE | Le numéro de licence FIE          |
| I2     | entier  | Rang        | Le rang dans le classement FIE    |
| I3     | decimal | Points      | Les points dans le classement FIE |

#### Exemple

Prenons l'exemple suivant :

```
DUPONT,Jean-François,18/3/1975,M,FRA;FRA M 18031975 00,1,264;;
```

C'est à dire les informations suivantes :

| Numéro | Type    | Contenu             | Explication                       |
|--------|---------|---------------------|-----------------------------------|
| I1     | chaine  | `FRA M 18031975 00` | Le numéro de licence FIE          |
| I2     | entier  | `1`                 | Le rang dans le classement FIE    |
| I3     | decimal | `264`               | Les points dans le classement FIE |


### Informations de la fédération nationale

Les champs suivants sont séparés par des `,` :

| Numéro | Type    | Contenu           | Explication                                                              |
|--------|---------|-------------------|--------------------------------------------------------------------------|
| N1     | chaine  | Licence nationale | Le numéro de licence nationale                                           |
| N2     | chaine  | Etat              | L'état ou la ligue du tireur quand le pays est divisé en états ou ligues |
| N3     | chaine  | Club              | Le club du tireur                                                        |
| N4     | entier  | Rang              | Le rang dans le classement national                                      |
| N5     | decimal | Points            | Les points dans le classement national                                   |
| N6     |         |                   | Réservé                                                                  |


*Note : Les fédérations nationales sont libres d'ajouter des champs ensuite.*

On obtient une chaîne du type :

```
G1,G2,G3,G4,G5;I1,I2,I3;N1,N2,N3,N4,N5,N6;
```

### Informations d'une compétition

On a des informations différentes selon si c'est un résultat ou une sauvegarde.

#### Pour un résultat

Lorsque c’est un résultat, deux champs :

| Numéro | Type    | Contenu | Explication                                                         |
|--------|---------|---------|---------------------------------------------------------------------|
| C1     | entier  | Rang    | Le rang à la fin de la compétition                                  |
| C2     | symbole |         | `T` pour participation au tableau, `P` pour éliminé dans les poules |

On obtient une chaîne du type :

```
G1,G2,G3,G4,G5;I1,I2,I3;N1,N2,N3,N4,N5,N6;C1,C2;
```

#### Pour une sauvegarde

Lorsque c’est une sauvegarde pendant des poules ou des tableaux, quatre champs :

| Numéro | Type    | Contenu | Explication                                                           |
|--------|---------|---------|-----------------------------------------------------------------------|
| C1     | entier  | Rang    | Rang d’origine du tour de poules ou du tableau (incluant les exempts) |
| C2     | symbole |         | `E` pour exempté, `P` pour poule, `T` pour tableau                    |
| C3     | entier  |         | Numéro de poule                                                       |
| C4     | entier  |         | Place dans la poule ou dans le tableau                                |

On obtient une chaîne du type :

```
G1,G2,G3,G4,G5;I1,I2,I3;N1,N2,N3,N4,N5,N6;C1,C2,C3,C4;
```


#### Pour une sauvegarde après classement des qualifiés

Lorsque c’est une sauvegarde après classement des qualifiés, deux champs :

| Numéro | Type    | Contenu | Explication                      |
|--------|---------|---------|----------------------------------|
| C1     | entier  | Rang    | Rang du classement des qualifiés |
| C2     | symbole |         | `Q` pour qualifié                |

On obtient une chaîne du type :

```
G1,G2,G3,G4,G5;I1,I2,I3;N1,N2,N3,N4,N5,N6;C1,C2;
```


#### Exemple d'informations sur les tireurs

Différents exemples dans différentes situations :

##### Exemple pour un classement FFE

Prenons l'exemple suivant (généré à la main à partir [d'ici](https://www.escrime-info.com/gregxml/Greg/BD/afficher.php?epreuve=17496&mode=fff))

```
VAN DE MERGHEL,Robin,14/03/2003,M,FRA,,;,,;057702,,CERCLE DE L'EPEE COGNAC,44,18484;
```

C'est à dire les informations suivantes :

| Numéro | Type    | Contenu                   | Explication                                                              |
|--------|---------|---------------------------|--------------------------------------------------------------------------|
| G1     | chaine  | `VAN DE MERGHEL`          | Le nom du tireur                                                         |
| G2     | chaine  | `Robin`                   | Le prénom du tireur                                                      |
| G3     | date    | `14/03/2003`              | La date de naissance du tireur                                           |
| G4     | symbole | `M`                       | `m` ou `f`                                                               |
| G5     | symbole | `FRA`                     | La nation du tireur                                                      |
| I1     | chaine  |                           | Le numéro de licence FIE                                                 |
| I2     | entier  |                           | Le rang dans le classement FIE                                           |
| I3     | decimal |                           | Les points dans le classement FIE                                        |
| N1     | chaine  | `057702`                  | Le numéro de licence nationale                                           |
| N2     | chaine  |                           | L'état ou la ligue du tireur quand le pays est divisé en états ou ligues |
| N3     | chaine  | `CERCLE DE L'EPEE COGNAC` | Le club du tireur                                                        |
| N4     | entier  | `44`                      | Le rang dans le classement national                                      |
| N5     | decimal | `18484`                   | Les points dans le classement national                                   |
| N6     |         |                           | Réservé                                                                  |


##### Exemple pour un résultat

Prenons l'exemple suivant ([source](https://www.escrime-info.com/gregxml/Greg/BD/afficher.php?epreuve=17496&mode=fff)) :

```
VAN DE MERGHEL,Robin,14/03/2003,M,FRA,,;,,;057702,,CERCLE DE L'EPEE COGNAC,,;11,t 
```

C'est à dire les informations suivantes :

| Numéro | Type    | Contenu                   | Explication                                                              |
|--------|---------|---------------------------|--------------------------------------------------------------------------|
| G1     | chaine  | `VAN DE MERGHEL`          | Le nom du tireur                                                         |
| G2     | chaine  | `Robin`                   | Le prénom du tireur                                                      |
| G3     | date    | `14/03/2003`              | La date de naissance du tireur                                           |
| G4     | symbole | `M`                       | `m` ou `f`                                                               |
| G5     | symbole | `FRA`                     | La nation du tireur                                                      |
| I1     | chaine  |                           | Le numéro de licence FIE                                                 |
| I2     | entier  |                           | Le rang dans le classement FIE                                           |
| I3     | decimal |                           | Les points dans le classement FIE                                        |
| N1     | chaine  | `057702`                  | Le numéro de licence nationale                                           |
| N2     | chaine  |                           | L'état ou la ligue du tireur quand le pays est divisé en états ou ligues |
| N3     | chaine  | `CERCLE DE L'EPEE COGNAC` | Le club du tireur                                                        |
| N4     | entier  |                           | Le rang dans le classement national                                      |
| N5     | decimal |                           | Les points dans le classement national                                   |
| N6     |         |                           | Réservé                                                                  |
| C1     | entier  | `11`                      | Le rang à la fin de la compétition                                       |
| C2     | symbole | `t`                       | `T` pour participation au tableau, `P` pour éliminé dans les poules      |

##### Exemple pour une sauvegarde

Prenons l'exemple suivant (génération à la main à partir [d'ici](https://www.escrime-info.com/gregxml/Greg/BD/afficher.php?epreuve=17496&mode=fff)) :
<!-- On dit que le tireur est dans une Poule, part 3 du classement, est dans la poule 4, et est 8 des poules -->

```
VAN DE MERGHEL,Robin,14/03/2003,M,FRA,,;,,;057702,,CERCLE DE L'EPEE COGNAC,,;3,P,4,8;
```

C'est à dire les informations suivantes :

| Numéro | Type    | Contenu                   | Explication                                                              |
|--------|---------|---------------------------|--------------------------------------------------------------------------|
| G1     | chaine  | `VAN DE MERGHEL`          | Le nom du tireur                                                         |
| G2     | chaine  | `Robin`                   | Le prénom du tireur                                                      |
| G3     | date    | `14/03/2003`              | La date de naissance du tireur                                           |
| G4     | symbole | `M`                       | `m` ou `f`                                                               |
| G5     | symbole | `FRA`                     | La nation du tireur                                                      |
| I1     | chaine  |                           | Le numéro de licence FIE                                                 |
| I2     | entier  |                           | Le rang dans le classement FIE                                           |
| I3     | decimal |                           | Les points dans le classement FIE                                        |
| N1     | chaine  | `057702`                  | Le numéro de licence nationale                                           |
| N2     | chaine  |                           | L'état ou la ligue du tireur quand le pays est divisé en états ou ligues |
| N3     | chaine  | `CERCLE DE L'EPEE COGNAC` | Le club du tireur                                                        |
| N4     | entier  |                           | Le rang dans le classement national                                      |
| N5     | decimal |                           | Les points dans le classement national                                   |
| N6     |         |                           | Réservé                                                                  |
| C1     | entier  | `3`                       | Rang d’origine du tour de poules ou du tableau (incluant les exempts)    |
| C2     | symbole | `P`                       | `E` pour exempté, `P` pour poule, `T` pour tableau                       |
| C3     | entier  | `4`                       | Numéro de poule                                                          |
| C4     | entier  | `8`                       | Place dans la poule ou dans le tableau                                   |



## Exemple de fichier

Voici un exemple de fichier fourni par la FIE :

### Fichier fourni par la FIE

Pour un classement FIE :

```
FFF;WIN;CLASSEMENT;FIE
7/6/2000;sabre;M;senior;Classement officiel de la FIE
DUPONT,Jean-François,18/3/1975,M,FRA;FRA M 18031975 00,1,264;;
MARTIN,René,2/10/1976,M,FRA;FRA M 02101976 00,2,251;;
...
```

### Fichier fourni par un organisateur de compétition

Pour les résultats d'une compétition :

```
FFF;WIN;COMPETITION;CERCLE D'ESCRIME DE DOURDAN
10/6/2000;sabre;M;senior;Coupe de la Ville de Dourdan 2000;Dourdan 2000
DUPONT,Jean-François,18/3/1975,M,FRA;FRA M 18031975 00,1,264;;8,T
MARTIN,René,2/10/1976,M,FRA;FRA M 02101976 00,2,251;;23,T
...
```

### Fichier de sauvegarde

Pour une sauvegarde de compétition en cours :

```
FFF;WIN;SAUVEGARDE;CERCLE D'ESCRIME DE DOURDAN
10/6/2000;sabre;M;senior;Coupe de la Ville de Dourdan 2000;Dourdan 2000
DUPONT,Jean-François,18/3/1975,M,FRA;FRA M 18031975 00,1,264;;4,E,,
MARTIN,René,2/10/1976,M,FRA;FRA M 02101976 00,2,251;;104,P,14,3
...
```

*Note : Dans la [documentation originelle](http://betton.escrime.free.fr/documents/BellePoule/doc/fff.pdf), dans l'exemple de fichier de sauvegarde, ils utilisent `COMPETITION` plutôt que `SAUVEGARDE` en deuxième ligne. C'est une erreur.*

# Sources

- [Documentation originelle](http://betton.escrime.free.fr/documents/BellePoule/doc/fff.pdf)
- [Competition de poitiers](https://www.escrime-info.com/gregxml/Greg/BD/afficher.php?epreuve=17496&mode=fff)