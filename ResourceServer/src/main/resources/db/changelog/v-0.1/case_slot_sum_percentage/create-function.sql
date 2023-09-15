CREATE FUNCTION case_slot_sum_percentage()
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

CREATE TRIGGER verify_sum_percentage
    AFTER INSERT OR UPDATE OR DELETE
    ON case_slots
    FOR EACH ROW
EXECUTE PROCEDURE case_slot_sum_percentage();

GO