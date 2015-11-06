CREATE SEQUENCE test_seq_one;
CREATE SEQUENCE test_seq_two START WITH 10;
CREATE TABLE test_table (
test_id INT DEFAULT test_seq_one.NEXTVAL PRIMARY KEY,
test_second_id INT DEFAULT  test_seq_two.NEXTVAL,
test_value VARCHAR(100),
test_int_ind INT);
CREATE INDEX ind_s_id ON test_table(test_second_id);
CREATE INDEX ind_int ON test_table(test_int_ind);

INSERT INTO test_table (test_value, test_int_ind ) VALUES ('Test value 1', 4);
INSERT INTO test_table (test_value, test_int_ind ) VALUES ('Test value 2', 5);
INSERT INTO test_table (test_value, test_int_ind ) VALUES ('Test value 3', 78);

CREATE ALIAS GET_BIGGER_THAN_THIRTY AS $$
String getBiggerThanThirty(java.sql.Connection conn) throws Exception{
StringBuilder sb = new StringBuilder();
java.sql.ResultSet rs = conn.createStatement().executeQuery(
"SELECT test_value FROM test_table WHERE test_int_ind > 30");
boolean first = true;
while(rs.next()){
if(first) {
first = false;
} else {
sb.append(", ");
}
sb.append(rs.getString(1));
}
return sb.toString();
}
$$;
SELECT GET_BIGGER_THAN_THIRTY();

CREATE VIEW test_view AS SELECT * FROM test_table WHERE test_int_ind > 10;

SELECT * FROM test_view;

--Dont forget to cleanup after yourself
--DROP VIEW test_view;
--DROP TABLE test_table;
--DROP SEQUENCE test_seq_one;
--DROP SEQUENCE test_seq_two;
--DROP ALIAS GET_BIGGER_THAN_THIRTY;