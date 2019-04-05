package com.nick.thegreat.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nick.thegreat.myapplication.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";

    private static final int DATABASE_VERSION = 3;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper( Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new QuizDbHelper(context.getApplicationContext());
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " + ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NUMBER + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE"
                + ")";



        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        fillCategoriesTable();

        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);

        onCreate(db);

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {

        super.onConfigure(db);

        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable()
    {
        Category c1 = new Category("Sports");

        addCategory(c1);

        Category c2 = new Category("Music");

        addCategory(c2);

        Category c3 = new Category("Math");

        addCategory(c3);
    }

    private void addCategory(Category category)
    {
        ContentValues cv = new ContentValues();

        cv.put(CategoriesTable.COLUMN_NAME, category.getName());

        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable()
    {
        Question q1 = new Question("Sports, Easy : Which team won the champions league 3" +
                " times in a row ?",
                "Real Madrid",
                "Barcelona",
                "Bayern Munich",
                "Juventus",
                1, Question.DIFFICULTY_EASY, Category.SPORTS);

        addQuestion(q1);

        Question q2 = new Question("Sports, Easy : Which team won the Euroleague " +
                "in 2018 ?",
                "Fenerbahce",
                "Real Madrid",
                "CSKA Moscow",
                "Olympiacos",
                2, Question.DIFFICULTY_EASY, Category.SPORTS);

        addQuestion(q2);

        Question q7 = new Question("Sports, Medium : Which team won the Bundesliga, " +
                "just a year after being promoted from the second league ? ",
                "Nurnberg",
                "Borussia Monchengladbach",
                "FC Koln",
                "Kaiserslautern",
                4, Question.DIFFICULTY_MEDIUM, Category.SPORTS);

        addQuestion(q7);

        Question q8 = new Question("Sports, Medium : How many medals did Russia win " +
                "in the 2016 olympic games ? ",
                "30",
                "46",
                "56",
                "60",
                3, Question.DIFFICULTY_MEDIUM, Category.SPORTS);

        addQuestion(q8);

        Question q9 = new Question("Sports, Hard : Who said in 1998, 'The ball does not " +
                "know how old you are' ? " ,
                "Michael Jordan",
                "Mark O'Meara",
                "Diego Maradona",
                "Boris Becker",
                2, Question.DIFFICULTY_HARD, Category.SPORTS);

        addQuestion(q9);

        Question q10 = new Question("Sports, Hard : How many people are there in an " +
                "official tug of war team ? ",
                "8",
                "10",
                "9",
                "11",
                1, Question.DIFFICULTY_HARD, Category.SPORTS);

        addQuestion(q10);

        Question q3 = new Question("Music, Easy : Who is the lead singer of " +
                "The Rolling Stones ? ",
                "John Lennon",
                "Roger Daltrey",
                "Mick Jagger",
                "Roger Waters",
                3, Question.DIFFICULTY_EASY, Category.MUSIC);

        addQuestion(q3);

        Question q4 = new Question("Music, Easy : Who is dubbed 'The king of pop' ? ",
                "Justin Bieber",
                "Elvis Presley",
                "George Michael",
                "Michael Jackson",
                4, Question.DIFFICULTY_EASY, Category.MUSIC);

        addQuestion(q4);

        Question q11 = new Question("Music, Medium : About whom The Moody Blues song " +
                "'Legend Of A Mind' was written ? ",
                "Albert Einstein",
                "Timothy Leary",
                "Isaac Newton",
                "Friedrich Nitzsche",
                2, Question.DIFFICULTY_MEDIUM, Category.MUSIC);

        addQuestion(q11);

        Question q12 = new Question("Music, Medium : Which compositor influenced the " +
                "guitarist Yngwie Malmsteen ? ",
                "Nicolo Paganini",
                "Johan Sebastian Bach",
                "Friedrich Schopin",
                "Wolfgang Amadeus Mozart",
                1, Question.DIFFICULTY_MEDIUM, Category.MUSIC);

        addQuestion(q12);

        Question q13 = new Question("Music, Hard : Roy Harper performs the guest vocals" +
                " for which of these classic rock songs ? ",
                "Take It To The Limit by The Eagles",
                "Have A Cigar by Pink Floyd",
                "Lithium by Nirvana",
                "Hats Off (To Roy Harper) by Led Zeppelin",
                2, Question.DIFFICULTY_HARD, Category.MUSIC);

        addQuestion(q13);

        Question q14 = new Question("Music, Hard : In 2007, who was awarded a special " +
                "Pulitzer Prize for 'masterful improvisation, supreme musicianship and iconic " +
                "centrality to the history of jazz' ? ",
                "Coleman Hawkins",
                "Stan Getz",
                "Charlie Parker",
                "John Coltrane",
                4, Question.DIFFICULTY_HARD, Category.MUSIC);

        addQuestion(q14);

        Question q5 = new Question("Math, Easy : How much is 5 times of 5 ? ",
                "15",
                "25",
                "55",
                "10",
                2, Question.DIFFICULTY_EASY, Category.MATH);

        addQuestion(q5);

        Question q6 = new Question("Math, Easy : What is the sum of angles in a " +
                "triangle ? ",
                "180 Degrees",
                "360 Degrees",
                "90 Degrees",
                "200 Degrees",
                1, Question.DIFFICULTY_EASY, Category.MATH);

        addQuestion(q6);

        Question q15 = new Question("Math, Medium : How much is 7 in the power of 3 ? ",
                "21",
                "360",
                "343",
                "84",
                3, Question.DIFFICULTY_MEDIUM, Category.MATH);

        addQuestion(q15);

        Question q16 = new Question("Math, Medium : What is the cube root of 512 ? ",
                "6",
                "7",
                "9",
                "8",
                4, Question.DIFFICULTY_MEDIUM, Category.MATH);

        addQuestion(q16);

        Question q17 = new Question("Math, Hard : What's the only place in this world" +
                " whose Fahrenheit and Celsius degrees can be equal ? ",
                "Antarctica at -40 degrees",
                "Alaska at -50 degrees",
                "Hawaii at 100 degrees",
                "Anywhere that exceeds the freezing point",
                1, Question.DIFFICULTY_HARD, Category.MATH);

        addQuestion(q17);

        Question q18 = new Question("Math, Hard : If Log 4 (x) = 12, then log 2 (x / 4)" +
                " is equal to ? ",
                "48",
                "22",
                "11",
                "-12",
                2, Question.DIFFICULTY_HARD, Category.MATH);

        addQuestion(q18);


    }

    private void addQuestion(Question question)
    {
        ContentValues cv = new ContentValues();

        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());

        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());

        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());

        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());

        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());

        cv.put(QuestionsTable.COLUMN_ANSWER_NUMBER, question.getAnswerNumber());

        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());

        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());

        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories()
    {
        List<Category> categoryList = new ArrayList<>();

        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME,
                null);

        if (c.moveToFirst())
        {
            do
            {
                Category category = new Category();

                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));

                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));

                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();

        return categoryList;
    }

    public ArrayList<Question> getAllQuestions()
    {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst())
        {
            do
            {
                Question question = new Question();

                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));

                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));

                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));

                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));

                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));

                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));

                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuestionsTable
                        .COLUMN_ANSWER_NUMBER)));

                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable
                        .COLUMN_DIFFICULTY)));

                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable
                        .COLUMN_CATEGORY_ID)));

                questionList.add(question);
            }
            while(c.moveToNext());
        }

        c.close();

        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty)
    {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND "  + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";

        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
                );

        if (c.moveToFirst())
        {
            do
            {
                Question question = new Question();

                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));

                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));

                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));

                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));

                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));

                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));

                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuestionsTable
                        .COLUMN_ANSWER_NUMBER)));

                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable
                        .COLUMN_DIFFICULTY)));

                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable
                        .COLUMN_CATEGORY_ID)));

                questionList.add(question);
            }
            while(c.moveToNext());
        }

        c.close();

        return questionList;
    }
}
