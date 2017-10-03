
--==========================  VIEWS  =====================--

/*  The following description tables concatinate all descriptions
    held in there respective tables into a single string.

    See funtions for details.
 */

CREATE VIEW EVENT_DESCRIPTION_TBL AS
    SELECT * FROM TABLE(CONCAT_EVENT_DESC) WHERE TEXT IS NOT NULL;

CREATE VIEW SKILL_DESCRIPTION_TBL AS
    SELECT * FROM TABLE(CONCAT_SKILL_DESC) WHERE TEXT IS NOT NULL;


/*  This following message views combine the information from several tables
    to produce an easily queriable view that will retrieve only the relavant
    messages for a given user and event time. The query below retrieves
    all relavant messages for user with id 3 and event_time with id 1.

    (note: If RECIEVER_ID = 0 it is a braodcast to all participants)

    SELECT * FROM EVENT_TIME_MESSAGE_VIEW WHERE
      EVENT_TIME_ID = 1 AND
        ((SENDER_ID = POSTER_ID  AND RECIEVER_ID = 0)
          OR RECIEVER_ID = 3 OR SENDER_ID = 3);
 */
CREATE VIEW EVENT_TIME_MESSAGE_VIEW AS
SELECT
    ETM.ID,
    ETM.SENDER_ID,
    ETM.EVENT_TIME_ID,
    ETM.MESSAGE,
    ETM.TIME_STAMP,
    COALESCE(ETMR.RECIEVER_ID, 0) AS RECIEVER_ID,
    COALESCE(ETMR.EVENT_TIME_MSG_ID, 0) AS EVENT_TIME_MSG_ID,
    EV.POSTER_ID
FROM EVENT_TIME_MESSAGE ETM
FULL JOIN EVENT_TIME_MESSAGE_RECIPIENT ETMR ON ETM.ID = ETMR.EVENT_TIME_MSG_ID
JOIN EVENT_TIME ET ON ET.ID = ETM.EVENT_TIME_ID
JOIN EVENT EV ON  EV.ID = ET.EVENT_ID
ORDER BY ETM.TIME_STAMP;

CREATE OR REPLACE VIEW EVENT_MESSAGE_VIEW AS
SELECT
    EVM.ID,
    EVM.SENDER_ID,
    EVM.MESSAGE,
    EVM.EVENT_ID,
    EVM.TIME_STAMP,
    COALESCE(EVMR.RECIEVER_ID, 0) AS RECIEVER_ID,
    COALESCE(EVMR.EVENT_MSG_ID, 0) AS EVENT_MSG_ID,
    EV.POSTER_ID
FROM EVENT_MESSAGE EVM
FULL JOIN EVENT_MESSAGE_RECIPIENT EVMR ON EVM.ID = EVMR.EVENT_MSG_ID
JOIN EVENT EV ON  EV.ID = EVM.EVENT_ID
ORDER BY EVM.TIME_STAMP;
