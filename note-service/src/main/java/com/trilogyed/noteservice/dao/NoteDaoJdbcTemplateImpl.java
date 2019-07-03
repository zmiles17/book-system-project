package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.model.Note;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NoteDaoJdbcTemplateImpl implements NoteDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String INSERT_NOTE_SQL =
            "insert into note (book_id, note) values (:bookId, :note)";

    private static final String SELECT_NOTE_SQL =
            "select * from note where note_id = :noteId";

    private static final String SELECT_NOTE_BY_BOOK_SQL =
            "select * from note where book_id = :bookId";

    private static final String SELECT_ALL_NOTES_SQL =
            "select * from note";

    private static final String UPDATE_NOTE_SQL =
            "update note set book_id = :bookId, note = :note where note_id = :id";



    @Override
    public Note addNote(Note note) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", note.getBookId());
        params.put("note", note.getNote());

        jdbcTemplate.update(INSERT_NOTE_SQL, params);

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", params, Integer.class);
        note.setNoteId(id);

        return note;
    }

    @Override
    public Note getNote(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("noteId", id);
        try {
            return jdbcTemplate.queryForObject(SELECT_NOTE_SQL, params, this::mapRowToNote);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Note getNoteByBook(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", id);
        try {
            return jdbcTemplate.queryForObject(SELECT_NOTE_BY_BOOK_SQL, params, this::mapRowToNote);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Note> getAllNotes() {
        return jdbcTemplate.query(SELECT_ALL_NOTES_SQL, this::mapRowToNote);
    }

    @Override
    public void updateNote(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId",)

    }

    @Override
    public void deleteNote(int id) {

    }

    private Note mapRowToNote(ResultSet rs, int rowNum) throws SQLException {
        Note note = new Note();
        note.setNoteId(rs.getInt("note_id"));
        note.setBookId(rs.getInt("book_id"));
        note.setNote(rs.getString("note"));
        return note;
    }
}
