/**
 * Author:  Amir
 * Created: 22 déc. 2023
 */

DELIMITER $

CREATE OR REPLACE PROCEDURE getUtilisateurParPrenom(IN prenom VARCHAR(20))
BEGIN
    SELECT *
    FROM UTILISATEUR
    WHERE LOWER(UTI_PRENOM) = LOWER(prenom);
END $

CREATE OR REPLACE PROCEDURE getTicketActivite (IN nomActivite VARCHAR(255), IN idUti VARCHAR(255))
BEGIN
    SELECT SUM(A.ACH_QUANTITE) - COUNT(C.CON_ID) AS totalTickets
    FROM ACHETERTICKET A
    INNER JOIN ACTIVITE Ac ON Ac.ACT_ID = A.ACT_ID
    LEFT JOIN CONSOMMERTICKET C ON C.ACH_ID = A.ACH_ID
    WHERE A.UTI_ID = idUti AND Ac.ACT_LIBELLE = nomActivite;
END $

CREATE OR REPLACE PROCEDURE consommerTicket(IN nomActivite VARCHAR(255), IN idUti VARCHAR(255), IN achID INT)
BEGIN
    UPDATE ACHETERTICKET A
    INNER JOIN ACTIVITE Ac ON Ac.ACT_ID = A.ACT_ID 
    SET ACH_QUANTITE = ACH_QUANTITE – 1
    WHERE A.UTI_ID = idUti AND Ac.ACT_LIBELLE = nomActivite AND achID = A.ACH_ID ; 
END $

DELIMITER $
CREATE OR REPLACE PROCEDURE verifierTicket(IN nomActivite VARCHAR(255), IN idUti VARCHAR(255))
BEGIN
	SELECT A.ACH_ID, SUM(ACH_QUANTITE) - COUNT(C.CON_ID) AS totalTickets
	FROM ACHETERTICKET A 
	INNER JOIN ACTIVITE Ac ON Ac.ACT_ID = A.ACT_ID  
        LEFT JOIN CONSOMMERTICKET C ON C.ACH_ID = A.ACH_ID
	WHERE A.UTI_ID = idUti AND Ac.ACT_LIBELLE = nomActivite;
END $
DELIMITER ;

DELIMITER $
CREATE OR REPLACE PROCEDURE CalculateDifference(IN id_uti VARCHAR(255), IN id_activite VARCHAR(3), OUT result INT)
BEGIN
    DECLARE sum_table1 INT;
    DECLARE sum_table2 INT;

    -- Calculate sum for table1
    SELECT SUM(ACH_QUANTITE) INTO sum_table1 FROM ACHETERTICKET A
    WHERE A.UTI_ID = id_uti AND A.ACT_ID = id_activite;

    -- Calculate sum for table2
    SELECT COUNT(CON_ID) INTO sum_table2 FROM CONSOMMERTICKET CT
    WHERE CT.UTI_ID = id_uti AND CT.ACT_ID = id_activite;

    -- Calculate the difference
    SET result = sum_table1 - sum_table2;
END $
DELIMITER ;

DELIMITER $
CREATE OR REPLACE PROCEDURE testVerifierTicket(IN nomActivite VARCHAR(255), IN idUti VARCHAR(255))
BEGIN
	SELECT A.ACH_ID, ACH_QUANTITE AS totalTickets
	FROM ACHETERTICKET A 
	INNER JOIN ACTIVITE Ac ON Ac.ACT_ID = A.ACT_ID  
	WHERE A.UTI_ID = idUti AND Ac.ACT_LIBELLE = nomActivite;
END $
DELIMITER ;

DELIMITER $
CREATE OR REPLACE PROCEDURE acheterTicket(IN actId VARCHAR(50), IN utiId VARCHAR(5), IN quantite INT)
BEGIN
	INSERT INTO ACHETERTICKET (ACT_ID, UTI_ID, ACH_DATE, ACH_QUANTITE) VALUES(actId, utiId, CURRENT_TIMESTAMP(), quantite);
END $

DELIMITER $
CREATE OR REPLACE PROCEDURE getLoginEleve(IN login VARCHAR(20))
BEGIN
    SELECT *
    FROM ELEVE
    WHERE ELELOGIN = LOWER(login);
END $
DELIMITER ;

CREATE OR REPLACE PROCEDURE getMontant(in cat_code int, in nomActivite VARCHAR(255))
BEGIN 
    SELECT TARIF 
    FROM TARIF T INNER JOIN ACTIVITE A ON A.ACT_ID = T.ACT_ID
    WHERE T.CAT_CODE = cat_code AND A.ACT_LIBELLE = nomActivite;
END $

DELIMITER $ 
CREATE OR REPLACE PROCEDURE consommeBDDTicket(in utiId VARCHAR(255), in nomActivite VARCHAR(255), achID INT)
BEGIN
    INSERT INTO CONSOMMERTICKET (ACT_ID, UTI_ID, ACH_ID, CON_DATE) VALUES(nomActivite, utiId, achID, CURRENT_TIMESTAMP());
END $

DELIMITER ;


DELIMITER $ 
CREATE OR REPLACE PROCEDURE getAchID(in id_uti VARCHAR(255), in id_activite VARCHAR(255))
BEGIN
    SELECT A.ACH_ID, A.ACH_QUANTITE
    FROM ACHETERTICKET A 
    INNER JOIN ACTIVITE Ac ON Ac.ACT_ID = A.ACT_ID
     WHERE A.UTI_ID = id_uti AND A.ACT_ID = id_activite;
END $

DELIMITER ;

DELIMITER $
CREATE OR REPLACE PROCEDURE getConsommerTicket(in id_uti VARCHAR(255), in id_activite VARCHAR(255))
BEGIN
    SELECT COUNT(*)
    FROM CONSOMMERTICKET
    WHERE UTI_ID = id_uti AND ACT_ID = id_activite;
END $

DELIMITER ;