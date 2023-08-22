CREATE FUNCTION sumCase (
    @field DATATYPE(?)
)
    RETURNS bigint
AS
BEGIN
    IF (select sum(percentage_wining) from case_slots group by case_id) = 1
        return 'True'
    return 'False'
END