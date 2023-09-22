CREATE OR REPLACE FUNCTION case_slot_sum_percentage()
    RETURNS TRIGGER AS
$$
BEGIN
    IF 100 = (SELECT SUM(win_rate)
              FROM case_slots
              WHERE case_id = COALESCE(NEW.case_id, OLD.case_id))
    THEN
        RETURN NEW;
    ELSE
        RAISE EXCEPTION 'Invalid sum';
    END IF;
END

$$ LANGUAGE plpgsql;

GO

ALTER FUNCTION case_slot_sum_percentage()
    RENAME TO case_slot_sum_win_rate;

GO

ALTER TRIGGER verify_sum_percentage ON case_slots RENAME TO verify_sum_win_rate;

GO