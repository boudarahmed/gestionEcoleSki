CREATE TABLE PERSONNE (
    IDPERSONNE    INTEGER PRIMARY KEY AUTOINCREMENT,
    NOM           TEXT,
    PRENOM        TEXT,
    DATENAISSANCE DATE,
    SEXE          CHAR    CHECK (SEXE = 'F' OR
                                 SEXE = 'M'),
    VILLE         TEXT,
    CODEPOSTAL    TEXT,
    NUMERO        TEXT,
    RUE           TEXT
);

CREATE TABLE ELEVE (
    IDELEVE   INTEGER PRIMARY KEY
                      REFERENCES PERSONNE (IDPERSONNE),
    ASSURANCE BOOLEAN
);

CREATE TABLE UTILISATEUR (
    IDUTILISATEUR INTEGER PRIMARY KEY
                          REFERENCES PERSONNE (IDPERSONNE),
    ADRESSEMAIL   TEXT,
    MOTDEPASSE    TEXT
);

CREATE TABLE CLIENT (
    IDCLIENT     INTEGER PRIMARY KEY
                         REFERENCES UTILISATEUR (IDUTILISATEUR),
    NUMEROCOMPTE TEXT
);

CREATE TABLE MONITEUR (
    IDMONITEUR       INTEGER PRIMARY KEY
                             REFERENCES UTILISATEUR (IDUTILISATEUR),
    SALAIREHORAIRE   DOUBLE,
    COURSPARTICULIER BOOLEAN
);

CREATE TABLE ADMINISTRATEUR (
    IDADMINISTRATEUR INTEGER PRIMARY KEY
                             REFERENCES UTILISATEUR (IDUTILISATEUR),
    POSTE            TEXT
);

CREATE TABLE RESERVATION (
    IDRESERVATION     INTEGER PRIMARY KEY AUTOINCREMENT,
    DATERESERVATION   DATE,
    STATUTRESERVATION TEXT    CHECK (STATUTRESERVATION = 'COMMANDER' OR
                                     STATUTRESERVATION = 'RESERVER' OR
                                     STATUTRESERVATION = 'PAYER'),
    PRIX              DOUBLE,
    IDCLIENT          INTEGER REFERENCES CLIENT (IDCLIENT),
    IDELEVE           INTEGER REFERENCES ELEVE (IDELEVE)
);

CREATE TABLE HORAIRE (
    IDHORAIRE INTEGER PRIMARY KEY AUTOINCREMENT,
    HEUREDEB  INTEGER,
    HEUREFIN  INTEGER
);

CREATE TABLE COURS (
    IDCOURS     INTEGER PRIMARY KEY AUTOINCREMENT,
    STATUTCOURS TEXT    CHECK (STATUTCOURS = 'ATTENTE' OR
                               STATUTCOURS = 'OUVERT' OR
                               STATUTCOURS = 'FERMER'),
    IDMONITEUR  INTEGER REFERENCES MONITEUR (IDMONITEUR),
    IDHORAIRE   INTEGER REFERENCES HORAIRE (IDHORAIRE)
);

CREATE TABLE LIGNECOURS (
    IDELEVE INTEGER REFERENCES ELEVE (IDELEVE),
    IDCOURS INTEGER REFERENCES COURS (IDCOURS),
    PRIMARY KEY (IDELEVE, IDCOURS)
);

CREATE TABLE LIGNERESERVATION (
    IDRESERVATION INTEGER REFERENCES RESERVATION (IDRESERVATION),
    IDCOURS INTEGER REFERENCES COURS (IDCOURS),
    PRIMARY KEY (IDRESERVATION, IDCOURS)
);

CREATE TABLE ACCREDITATION (
    IDACCREDITATION INTEGER PRIMARY KEY AUTOINCREMENT,
    SPORT           TEXT,
    AGEMIN          INTEGER,
    AGEMAX          INTEGER
);

CREATE TABLE SEMAINE (
    IDSEMAINE     INTEGER PRIMARY KEY AUTOINCREMENT,
    DATEDEB       DATE,
    DATEFIN       DATE,
    CONGESCOLAIRE BOOLEAN
);

CREATE TABLE LIGNEACCREDITATION (
    IDMONITEUR      INTEGER REFERENCES MONITEUR (IDMONITEUR),
    IDACCREDITATION INTEGER REFERENCES ACCREDITATION (IDACCREDITATION),
    PRIMARY KEY (IDMONITEUR, IDACCREDITATION)
);

CREATE TABLE LIGNESEMAINE (
    IDMONITEUR      INTEGER REFERENCES MONITEUR (IDMONITEUR),
    IDSEMAINE INTEGER REFERENCES SEMAINE (IDSEMAINE),
    PRIMARY KEY (IDMONITEUR, IDSEMAINE)
);

CREATE TABLE TYPECOURS (
    IDTYPECOURS     INTEGER PRIMARY KEY AUTOINCREMENT,
    NIVEAU          TEXT,
    MINELEVE        INTEGER,
    MAXELEVE        INTEGER,
    PRIX            DOUBLE,
    IDACCREDITATION INTEGER REFERENCES ACCREDITATION (IDACCREDITATION)
);

CREATE TABLE COURSPARTICULIER (
    IDCOURSPARTICULIER INTEGER PRIMARY KEY
                               REFERENCES COURS (IDCOURS),
    DATE               DATE,
    CONGESCOLAIRE      BOOLEAN,
    PRIX               DOUBLE,
    IDACCREDITATION    INTEGER REFERENCES ACCREDITATION (IDACCREDITATION)
);

CREATE TABLE COURSCOLLECTIF (
    IDCOURSCOLLECTIF INTEGER PRIMARY KEY
                             REFERENCES COURS (IDCOURS),
    IDSEMAINE        INTEGER REFERENCES SEMAINE (IDSEMAINE),
    IDTYPECOURS      INTEGER REFERENCES TYPECOURS (IDTYPECOURS)
);