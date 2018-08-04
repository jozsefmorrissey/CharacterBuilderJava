
--==========================  FUNCTIONS  =====================--

-- Prints PROFILE_LINK_TABLE's, used for debugging.
create or replace FUNCTION PRINT_TABLE_PROFILE_LINKS(TABLE_NAME VARCHAR2, TBL PROFILE_LINK_TABLE)
    RETURN NUMBER
    IS
    BEGIN
        DBMS_OUTPUT.PUT_LINE(TABLE_NAME || ':');
        FOR CLM IN (SELECT * FROM TABLE(TBL))
        LOOP
            DBMS_OUTPUT.PUT_LINE(CLM.USER_ID_1 ||  ':' || CLM.USER_ID_2);
        END LOOP;
        RETURN 1;
    END;
/

-- Prints LINK_TABLE's, used for debugging.
create or replace FUNCTION PRINT_TABLE_LINKS(TABLE_NAME VARCHAR2, TBL LINK_TABLE)
    RETURN NUMBER
    IS
    BEGIN
        DBMS_OUTPUT.PUT_LINE(TABLE_NAME || ':');
        FOR CLM IN (SELECT * FROM TABLE(TBL))
        LOOP
            DBMS_OUTPUT.PUT_LINE(CLM.USER_ID);
        END LOOP;
        RETURN 1;
    END;
/

-- Used to combine two LINK_TABLE's
create or replace FUNCTION CONCAT_TABLE_LINK_ARR(ARR1 LINK_TABLE, ARR2 LINK_TABLE)
    RETURN LINK_TABLE
    IS
    IND NUMBER;
    RET_VAL LINK_TABLE := LINK_TABLE();
    BEGIN
        IND := 0;
        FOR CLM IN (SELECT * FROM TABLE(ARR1))
        LOOP
            IND := IND + 1;
            RET_VAL.EXTEND;
            RET_VAL(RET_VAL.COUNT) := LINK_TYPE(IND, CLM.USER_ID, CLM.CREATOR_ID, CLM.IS_GOOD, CLM.REASON);
        END LOOP;

        FOR CLM IN (SELECT * FROM TABLE(ARR2))
        LOOP
            IND := IND + 1;
            RET_VAL.EXTEND;
            RET_VAL(RET_VAL.COUNT) := LINK_TYPE(IND, CLM.USER_ID, CLM.CREATOR_ID, CLM.IS_GOOD, CLM.REASON);
        END LOOP;
        RETURN RET_VAL;
    END;
/

-- Used to combine PROFILE_LINK_TABLE's...
-- A better man could probably do this with select and insert.
create or replace FUNCTION CONCAT_TABLE_PROFILE_LINK(ARR1 PROFILE_LINK_TABLE, ARR2 PROFILE_LINK_TABLE)
    RETURN PROFILE_LINK_TABLE
    IS
    IND NUMBER;
    RET_VAL PROFILE_LINK_TABLE := PROFILE_LINK_TABLE();
    BEGIN
        FOR CLM IN (SELECT * FROM TABLE(ARR1))
        LOOP
            IND := IND + 1;
            RET_VAL.EXTEND;
            RET_VAL(RET_VAL.COUNT) := PROFILE_LINK_TYPE(IND, CLM.USER_ID_1, CLM.USER_ID_2, CLM.CREATOR_ID, CLM.IS_GOOD, CLM.REASON);
        END LOOP;

        FOR CLM IN (SELECT * FROM TABLE(ARR2))
        LOOP
            IND := IND + 1;
            RET_VAL.EXTEND;
            RET_VAL(RET_VAL.COUNT) := PROFILE_LINK_TYPE(IND, CLM.USER_ID_1, CLM.USER_ID_2,  CLM.CREATOR_ID, CLM.IS_GOOD, CLM.REASON);
        END LOOP;
        RETURN RET_VAL;
    END;
/

-- THIS FUNCTION IS TO BE USED TO CONCATINATE
-- ALL TEXTS WITH THE TEXTS SHARING THE SAME ID
create or replace FUNCTION CONCAT_DESCRIPTIONS(DESC_LIST DESCRIPTION_LIST_TABLE)
    RETURN DESCRIPTION_TABLE
    IS
    TEXT CLOB;
    DESC_ID NUMBER;
    CURR_DESC_ID NUMBER;
    TBL_COUNT NUMBER;
    RET_VAL DESCRIPTION_TABLE := DESCRIPTION_TABLE();
    BEGIN

        DESC_ID := 0;
        FOR O IN (SELECT * FROM TABLE(DESC_LIST))
        LOOP
            CURR_DESC_ID := O.ID;

         IF CURR_DESC_ID != DESC_ID THEN
            RET_VAL.EXTEND;
            RET_VAL(RET_VAL.COUNT) := DESCRIPTION_TYPE(DESC_ID, TEXT);
            DESC_ID := CURR_DESC_ID;
            TEXT := '';
        END IF;

            TEXT := TEXT || O.TEXT;
        END LOOP;
        RET_VAL.EXTEND;
        RET_VAL(RET_VAL.COUNT) := DESCRIPTION_TYPE(CURR_DESC_ID, TEXT);
        RETURN RET_VAL;
    END;
/

-- THIS FUNCTION SIMPLY ORDERS THE EVENT_DESCRIPTION TABLE
-- ALLOWING CONCAT_DESCRIPTIONS TO CONCATINATE ALL WITH
-- THE SAME IDS IN THE ORDER DICTATED BY POSITION.
create or replace FUNCTION CONCAT_EVENT_DESC
    RETURN DESCRIPTION_TABLE
    IS
    DESC_LIST DESCRIPTION_LIST_TABLE;
    RET_VAL DESCRIPTION_TABLE := DESCRIPTION_TABLE();
    BEGIN
        SELECT * BULK COLLECT INTO DESC_LIST FROM
            (select DESCRIPTION_LIST(EVENT_ID, POSITION, DESCRIPTION)
            from EVENT_DESCRIPTION
            ORDER BY EVENT_ID, POSITION);
        RETURN CONCAT_DESCRIPTIONS(DESC_LIST);
    END;
/

-- THIS FUNCTION SIMPLY ORDERS THE EVENT_DESCRIPTION TABLE
-- ALLOWING CONCAT_DESCRIPTIONS TO CONCATINATE ALL WITH
-- THE SAME IDS IN THE ORDER DICTATED BY POSITION.
create or replace FUNCTION CONCAT_SKILL_DESC
    RETURN DESCRIPTION_TABLE
    IS
    DESC_LIST DESCRIPTION_LIST_TABLE;
    RET_VAL DESCRIPTION_TABLE := DESCRIPTION_TABLE();
    BEGIN
        SELECT * BULK COLLECT INTO DESC_LIST FROM
            (select DESCRIPTION_LIST(SKILL_MAP_ID, POSITION, DESCRIPTION)
            from SKILL_DESCRIPTION
            ORDER BY SKILL_MAP_ID, POSITION);
        RETURN CONCAT_DESCRIPTIONS(DESC_LIST);
    END;
/

-- THIS FUNCTION SIMPLY ORDERS THE EVENT_DESCRIPTION TABLE
-- ALLOWING CONCAT_DESCRIPTIONS TO CONCATINATE ALL WITH
-- THE SAME IDS IN THE ORDER DICTATED BY POSITION.
create or replace FUNCTION CONCAT_USER_DESC
    RETURN DESCRIPTION_TABLE
    IS
    DESC_LIST DESCRIPTION_LIST_TABLE;
    RET_VAL DESCRIPTION_TABLE := DESCRIPTION_TABLE();
    BEGIN
        SELECT * BULK COLLECT INTO DESC_LIST FROM
            (select DESCRIPTION_LIST(USER_ID, POSITION, DESCRIPTION)
            from USER_DESCRIPTION
            ORDER BY USER_ID, POSITION);
        RETURN CONCAT_DESCRIPTIONS(DESC_LIST);
    END;
/

-- This function simply returns a PROFILE_LINK_TABLE of the
-- search results from the actual PROFILE_LINK table refining
-- results to where USER_ID_1 = S_ID or USER_ID_2 = S_ID.
create or replace FUNCTION FIND_PROFILE_LINKS(S_ID NUMBER)
  RETURN PROFILE_LINK_TABLE
  IS
  LIST_LINKS PROFILE_LINK_TABLE;

  RET_VAL PROFILE_LINK_TABLE := PROFILE_LINK_TABLE();
  BEGIN
      SELECT * BULK COLLECT INTO LIST_LINKS FROM
          (SELECT PROFILE_LINK_TYPE(ID, USER_ID_1, USER_ID_2, CREATOR_ID, IS_GOOD, REASON)
            FROM PROFILE_LINK
            WHERE USER_ID_2 = S_ID OR USER_ID_1 = S_ID);
      RETURN LIST_LINKS;
END;
/

-- This function returns a LINK_TABLE for userIds that don't
-- exist within the HAY_STACK LINK_TABLE.
create or replace FUNCTION GET_NEW_LINKS(HAY_STACK LINK_TABLE, NEEDLES PROFILE_LINK_TABLE)
    RETURN LINK_TABLE
    IS
    C NUMBER;
    NEW_LINKS LINK_TABLE := LINK_TABLE();
    BEGIN
        FOR PL IN (SELECT * FROM TABLE(NEEDLES))
        LOOP
            SELECT COUNT(*) INTO C FROM TABLE(HAY_STACK) WHERE USER_ID = PL.USER_ID_1;
            IF C = 0 THEN
                NEW_LINKS.EXTEND;
                NEW_LINKS(NEW_LINKS.COUNT) := LINK_TYPE(PL.ID, PL.USER_ID_1, PL.CREATOR_ID, PL.IS_GOOD, PL.REASON);
            END IF;

            SELECT COUNT(*) INTO C FROM TABLE(HAY_STACK) WHERE USER_ID = PL.USER_ID_2;
            IF C = 0 THEN
                NEW_LINKS.EXTEND;
                NEW_LINKS(NEW_LINKS.COUNT) := LINK_TYPE(PL.ID, PL.USER_ID_2, PL.CREATOR_ID, PL.IS_GOOD, PL.REASON);
            END IF;

        END LOOP;
    RETURN NEW_LINKS;
END;
/

-- This function returns a reference to all profile links
-- that can be connected to a given userId by including all
-- links to linked ids aswell, in the form of a LINK_TABLE.
create or replace FUNCTION GET_ALL_LINKS(S_ID NUMBER)
  RETURN LINK_TABLE
IS
  UNIQUE_LINKS LINK_TABLE := LINK_TABLE();
  NEW_UNIQUE_LINKS LINK_TABLE := LINK_TABLE();

  FOUND_PL PROFILE_LINK_TABLE := PROFILE_LINK_TABLE();

  LINK_COUNT NUMBER;
  PROCESSED NUMBER;

  CURR LINK_TYPE;

  RET_VAL LINK_TABLE := LINK_TABLE();
BEGIN
    UNIQUE_LINKS.EXTEND;
    UNIQUE_LINKS(UNIQUE_LINKS.COUNT) := LINK_TYPE(0, S_ID, 0, 0, '');

    LINK_COUNT := 1;
    PROCESSED := 0;

    WHILE LINK_COUNT > PROCESSED
    LOOP
        PROCESSED := PROCESSED + 1;
        CURR := UNIQUE_LINKS(PROCESSED);

        FOUND_PL := FIND_PROFILE_LINKS(CURR.USER_ID);

        NEW_UNIQUE_LINKS := GET_NEW_LINKS(UNIQUE_LINKS, FOUND_PL);

        UNIQUE_LINKS := CONCAT_TABLE_LINK_ARR(UNIQUE_LINKS, NEW_UNIQUE_LINKS);

        SELECT COUNT(*) INTO LINK_COUNT FROM TABLE(UNIQUE_LINKS);
    END LOOP;
    RETURN UNIQUE_LINKS;
END;
/

-- This function finds all event messages relavant to a
-- given user.
create or replace FUNCTION FIND_EVENT_MESSAGES(U_ID NUMBER)
  RETURN EVENT_MESSAGE_TABLE
  IS
  LIST_LINKS EVENT_MESSAGE_TABLE;

  RET_VAL EVENT_MESSAGE_TABLE := EVENT_MESSAGE_TABLE();
  BEGIN
    SELECT * BULK COLLECT INTO LIST_LINKS FROM
        (Select EVENT_MESSAGE_TYPE(evm.ID, evm.sender_id, evm.event_id, emr.reciever_id, evm.message, evm.time_stamp)
        from event e
            join event_time et on et.EVENT_ID = e.ID
            join event_message evm on e.id = evm.EVENT_ID
            full join event_message_recipient emr on emr.event_msg_id = evm.ID
            join PARTICIPANT p on p.EVENT_TIME_ID = et.id
            where evm.SENDER_ID = U_ID and p.USER_ID = U_ID       -- If it was sent by user
                or (evm.SENDER_ID = e.POSTER_ID and
                    U_ID = p.USER_ID and emr.Reciever_id is null)     -- If message is blasted out from poster to everyone
                or emr.RECIEVER_ID = U_ID and p.USER_ID = U_ID    -- If message was sent directly to user
                or U_ID = e.POSTER_ID and p.user_id = U_ID);      -- If user is poster
    RETURN LIST_LINKS;
END;
/

-- This function finds all event_time messages relavant to
-- a given user.
create or replace FUNCTION FIND_EVENT_TIME_MESSAGES(U_ID NUMBER)
  RETURN EVENT_TIME_MESSAGE_TABLE
  IS
  LIST_LINKS EVENT_TIME_MESSAGE_TABLE;

  RET_VAL EVENT_TIME_MESSAGE_TABLE := EVENT_TIME_MESSAGE_TABLE();
  BEGIN
    SELECT * BULK COLLECT INTO LIST_LINKS FROM
        (Select EVENT_TIME_MESSAGE_TYPE(evtm.ID, evtm.sender_id, evtm.event_time_id, etmr.reciever_id, evtm.message, evtm.time_stamp)
            from event e
            join event_time et on et.EVENT_ID = e.id
            join event_time_message evtm on et.id = evtm.EVENT_TIME_ID
            full join event_time_message_recipient etmr on etmr.event_time_msg_id = evtm.ID
            join participant p on p.event_time_id = et.id
            where ((evtm.SENDER_ID = U_ID or etmr.RECIEVER_ID = U_ID)
                    and p.user_id = U_ID)                           -- Message was sent to or sent by user
                or (evtm.SENDER_ID = e.POSTER_ID and
                    etmr.RECIEVER_ID is null and p.user_id = U_ID)  -- Message blasted to everyone
                or U_ID = e.POSTER_ID and p.user_id = U_ID);           -- if user is poster
    RETURN LIST_LINKS;
END;
/
