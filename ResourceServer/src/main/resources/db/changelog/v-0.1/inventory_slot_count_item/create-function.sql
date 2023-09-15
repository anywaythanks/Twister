CREATE FUNCTION inventory_slot_count_item()
    RETURNS TRIGGER AS
$$
BEGIN
    IF 1 in (select case
                        when count(id) > 1 then 1
                        else 0
                        end
             from inventory_slots
             where inventory_id = new.inventory_id
               and item_id = new.item_id) THEN
        RAISE EXCEPTION 'Non-unique item in inventory';
    ELSE
        RETURN NEW;
    END IF;
END

$$ LANGUAGE plpgsql;

GO

CREATE TRIGGER verify_count_item
    AFTER INSERT OR UPDATE
    ON inventory_slots
    FOR EACH ROW
EXECUTE PROCEDURE inventory_slot_count_item();

GO