DELETE FROM MOVIESCTRL;

UPDATE MOVIES_IN SET POLLING_STRATEGY = 'ShadowTable';

COMMIT;