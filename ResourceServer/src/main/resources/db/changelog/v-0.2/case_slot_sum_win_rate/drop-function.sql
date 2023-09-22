ALTER FUNCTION case_slot_sum_win_rate()
    RENAME TO case_slot_sum_percentage;

GO

ALTER TRIGGER verify_sum_win_rate ON case_slots RENAME TO verify_sum_percentage;

GO

CREATE OR REPLACE FUNCTION case_slot_sum_percentage()
    RETURNS TRIGGER AS
$$
BEGIN
    IF 0 in (select case
                   when sum(percentage_wining) = 100 then 1
                   else 0
                   end
        from case_slots
        where case_id = new.case_id
        group by case_id) THEN
        RAISE EXCEPTION 'Invalid sum';
    ELSE
        RETURN NEW;
    END IF;
END

$$ LANGUAGE plpgsql;

GO