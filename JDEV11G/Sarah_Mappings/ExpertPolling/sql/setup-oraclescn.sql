DROP TABLE MOVIE_REVIEWS_IN;
DROP TABLE MOVIES_IN;

CREATE TABLE MOVIES_IN
(
TITLE VARCHAR2 (50) NOT NULL,
DIRECTOR VARCHAR2 (20),
STARRING VARCHAR2 (100),
SYNOPSIS VARCHAR2 (255),
GENRE VARCHAR2 (70),
RUN_TIME NUMBER,
RELEASE_DATE DATE,
RATED VARCHAR2 (6),
RATING VARCHAR2 (4),
VIEWER_RATING VARCHAR2 (5),
STATUS VARCHAR2 (11),
TOTAL_GROSS NUMBER,
DELETED VARCHAR(5),
SEQUENCENO NUMBER,
LAST_UPDATED DATE,
POLLING_STRATEGY VARCHAR2(30)
) ROWDEPENDENCIES
;
ALTER TABLE MOVIES_IN
ADD CONSTRAINT MOVIES_IN_PK1 PRIMARY KEY
(
TITLE
)
 ENABLE
;

CREATE TABLE MOVIE_REVIEWS_IN
(
TITLE VARCHAR2 (50) CONSTRAINT FK_MOVIE_REVIEWS_IN_MOVIES REFERENCES MOVIES_IN,
CRITIC VARCHAR2 (20) NOT NULL,
SOURCE VARCHAR2 (30),
GRADE VARCHAR2 (4),
QUOTE VARCHAR(255),
DELETED VARCHAR(5),
SEQUENCENO NUMBER,
LAST_UPDATED DATE
) ROWDEPENDENCIES;

ALTER TABLE MOVIE_REVIEWS_IN
ADD CONSTRAINT MOVIE_REVIEWS_IN_PK1 PRIMARY KEY
(
TITLE, CRITIC
)
 ENABLE
;

DROP TABLE MOVIE_REVIEWS;
DROP TABLE MOVIES;

CREATE TABLE MOVIES
(
TITLE VARCHAR2 (50) NOT NULL,
DIRECTOR VARCHAR2 (20),
STARRING VARCHAR2 (100),
SYNOPSIS VARCHAR2 (255),
GENRE VARCHAR2 (70),
RUN_TIME NUMBER,
RELEASE_DATE DATE,
RATED VARCHAR2 (6),
RATING VARCHAR2 (4),
VIEWER_RATING VARCHAR2 (5),
STATUS VARCHAR2 (11),
TOTAL_GROSS NUMBER,
DELETED VARCHAR(5),
SEQUENCENO NUMBER,
LAST_UPDATED DATE,
POLLING_STRATEGY VARCHAR2(30)
)
;
ALTER TABLE MOVIES
ADD CONSTRAINT MOVIES_PK1 PRIMARY KEY
(
TITLE
)
 ENABLE
;

CREATE TABLE MOVIE_REVIEWS
(
TITLE VARCHAR2 (50) CONSTRAINT FK_MOVIE_REVIEWS_MOVIES REFERENCES MOVIES,
CRITIC VARCHAR2 (20) NOT NULL,
SOURCE VARCHAR2 (30),
GRADE VARCHAR2 (4),
QUOTE VARCHAR(255),
DELETED VARCHAR(5),
SEQUENCENO NUMBER,
LAST_UPDATED DATE
);

ALTER TABLE MOVIE_REVIEWS
ADD CONSTRAINT MOVIE_REVIEWS_PK1 PRIMARY KEY
(
TITLE, CRITIC
)
 ENABLE
;


DROP TABLE MOVIESCTRL;

CREATE TABLE MOVIESCTRL
(
MOVIE VARCHAR2 (50) NOT NULL
)
;

DROP TABLE SEQUENCING_HELPER;

CREATE TABLE SEQUENCING_HELPER
(
TABLE_NAME VARCHAR2 (32),
LAST_READ_ID NUMBER,
LAST_UPDATED DATE
);

INSERT INTO MOVIES_IN VALUES ('Meet the Fockers', 'Jay Roach', 'Ben Stiller, Robert De Niro, Barbra Streisand, Dustin Hoffman', null, 'Comedy, New Release', 144, to_date('12-22-2004','mm-dd-yyyy'), 'PG-13', 'B-', '3', 'In Theaters', 163400000, 'TRUE', 1, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('Lemony Snicket''s A Series of Unfortunate Events', 'Brad Silberling', 'Jim Carrey, Liam Aiken, Meryl Streep, Emily Browning', null, 'Family and Children, Action and Adventure, Comedy, New Release', 113, to_date('12-17-2004','mm-dd-yyyy'), 'PG-13', 'C+', '3.5', 'In Theaters', 94750000, 'TRUE', 2, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('The Aviator', 'Martin Scorsese', 'Leonardo DiCaprio, Kate Beckinsale, Alec Baldwin, Cate Blanchett', null, 'Drama, New Release', 166, to_date('12-17-2004','mm-dd-yyyy'), 'PG-13', 'B', '3.5', 'In Theaters', 31080000, 'TRUE', 3, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('Fat Albert', 'Joel Zwick', 'Kenan Thompson, Kyla Pratt, Jeremy Suarez, Raven', null, 'Family and Children, Comedy, New Release', 100, to_date('12-25-2004','mm-dd-yyyy'), 'PG-13', 'C', '3.5', 'In Theaters', 33880000, 'TRUE', 4, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('Ocean''s Twelve', 'Steven Soderbergh', 'George Clooney, Julia Roberts, Brad Pitt, Catherine Zeta-Jones', null, 'Action and Adventure, Comedy', 120, to_date('12-10-2004','mm-dd-yyyy'), 'PG-13', 'D+', '2', 'In Theaters', 106890000, 'TRUE', 5, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('National Treasure', 'Jon Turteltaub', 'Nicolas Cage, Sean Bean, Hunter Gomez, Diane Kruger', null, 'Action and Adventure, Comedy', 125, to_date('11-19-2004','mm-dd-yyyy'), 'PG-13', 'C', '4', 'In Theaters', 154760000, 'TRUE', 6, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('Spanglish', 'James L. Brooks', 'Adam Sandler, Tea Leoni, Paz Vega, Cloris Leachman', null, 'Comedy, Drama, New Release', 128, to_date('12-17-2004','mm-dd-yyyy'), 'PG-13', 'D', '3', 'In Theaters', 31030000, 'TRUE', 7, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('The Polar Express', 'Robert Zemeckis', 'Tom Hanks, Hayden McFarland, Connor Matheus, Peter Scolari', null, 'Family and Children, Fantasy, Animation', 100, to_date('11-10-2004','mm-dd-yyyy'), 'PG-13', 'C+', '4', 'In Theaters', 155250000, 'TRUE', 8, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('Andrew Lloyd Webber''s The Phantom of the Opera', 'Joel Schumacher', 'Gerard Butler, Emmy Rossum, Patrick Wilson, Miranda Richardson', null, 'Drama, Romance, Musicals, New Release', 143, to_date('12-22-2004','mm-dd-yyyy'), 'PG-13', 'B-', '4.5', 'In Theaters', 16250000, 'TRUE', 9, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('Darkness', 'Jaume Balaguero', 'Anna Paquin, Lena Olin, Iain Glen, Giancarlo Giannini', null, 'Horror, Science Fiction, New Release', 102, to_date('12-25-2004','mm-dd-yyyy'), 'PG-13', 'D', '1', 'In Theaters', 16500000, 'TRUE', 10, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('War of the Worlds (2005)', 'Steven Spielberg', 'Tom Cruise, Dakota Fanning, Miranda Otto, Tim Robbins', 
    null, 'Science Fiction, New Release', 112, to_date('06-29-2005','mm-dd-yyyy'), 
    'PG-13', 'B', 'B', 'In Theaters', 113282000, 'TRUE', 11, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('Batman Begins', 'Christopher Nolan', 'Christian Bale, Michael Caine, Liam Neeson, Katie Holmes', 
    null, 'Action and Adventure, Drama, New Release', 140, to_date('06-15-2005','mm-dd-yyyy'), 
    'PG-13', 'B+', 'A-', 'In Theaters', 154146000, 'TRUE', 12, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('Mr. and Mrs. Smith', 'Doug Liman', 'Brad Pitt, Angelina Jolie, Vince Vaughn, Adam Brody', 
    null, 'Action and Adventure, Romance, New Release', 120, to_date('06-10-2005','mm-dd-yyyy'), 
    'PG-13', 'C+', 'B', 'In Theaters', 146058000, 'TRUE', 13, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('Bewitched', 'Nora Ephron', 'Nicole Kidman, Will Ferrell, Shirley MacLaine, Michael Caine', 
    null, 'Comedy, New Release', 98, to_date('06-24-2005','mm-dd-yyyy'), 
    'PG-13', 'C', 'B-', 'In Theaters', 40306000, 'TRUE', 14, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('Herbie: Fully Loaded', 'Angela Robinson', 'Lindsay Lohan, Justin Long, Breckin Meyer, Matt Dillon', 
    null, 'Family and Children, Action and Adventure, Comedy, New Release', 101, to_date('06-22-2005','mm-dd-yyyy'), 
    'G', 'C+', 'C', 'In Theaters', 36840000, 'TRUE', 15, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('Madagascar', 'Tom McGrath', 'Ben Stiller, Chris Rock, David Schwimmer, Jada Pinkett Smith', 
    null, 'Family and Children, Animation, Comedy', 86, to_date('05-27-2005','mm-dd-yyyy'), 
    'PG', 'B-', 'B', 'In Theaters', 172400000, 'TRUE', 16, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('Rebound', 'Steve Carr', 'Martin Lawrence, Wendy Raquel Robinson, Breckin Meyer, Horatio Sanz', 
     null, 'Comedy, New Release', 103, to_date('07-01-2005','mm-dd-yyyy'), 
     'PG', 'C-', 'C+', 'In Theaters', 6000000, 'TRUE', 17, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('Star Wars: Episode III - Revenge of the Sith', 'George Lucas', 'Hayden Christensen, Ewan McGregor, Natalie Portman, Samuel L. Jackson', 
    null, 'Action and Adventure, Science Fiction', 146, to_date('05-19-2005','mm-dd-yyyy'), 
    'PG-13', 'B+', 'B+', 'In Theaters', 366471000, 'TRUE', 18, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('The Longest Yard', 'Peter Segal', 'Adam Sandler, Chris Rock, Nelly, Burt Reynolds', 
    null, 'Comedy', 109, to_date('05-27-2005','mm-dd-yyyy'), 
    'PG-13', 'C+', 'B+', 'In Theaters', 148213000, 'TRUE', 19, sysdate, null);

INSERT INTO MOVIES_IN VALUES ('George A . Romero''s Land of the Dead', 'George A. Romero', 'Simon Baker, Dennis Hopper, John Leguizamo, Asia Argento', 
    null, 'Horror, Science Fiction, Action and Adventure, New Release', 93, to_date('06-24-2005','mm-dd-yyyy'), 
    'R', 'B', 'B-', 'In Theaters', 16739000, 'TRUE', 20, sysdate, null);


INSERT INTO MOVIE_REVIEWS_IN VALUES ('War of the Worlds (2005)', 'Roger Ebert',
    'Chicago Sun-Times', 'C', '"...a big, clunky movie containing some sensational sights but lacking the zest and joyous energy we expect from Steven Spielberg."',
    'TRUE', 101, sysdate);

INSERT INTO MOVIE_REVIEWS_IN VALUES ('War of the Worlds (2005)', 'Eric Meyerson',
    'filmcritic.com', 'A-', '"...the greatest alien invasion movie ever."',
    'TRUE', 102, sysdate);

INSERT INTO MOVIE_REVIEWS_IN VALUES ('Batman Begins', 'Roger Ebert',
    'Chicago Sun-Times', 'A', '"The movie works dramatically in addition to being an entertainment."',
    'TRUE', 103, sysdate);

INSERT INTO MOVIE_REVIEWS_IN VALUES ('Batman Begins', 'Michael Wilmington',
    'Chicago Tribune', 'B', '"...one of the artier, more noir-drenched, psychologically tortured and memorable of all the recent big comic-book hero movies."',
    'TRUE', 104, sysdate);

INSERT INTO MOVIE_REVIEWS_IN VALUES ('Mr. and Mrs. Smith', 'Roger Ebert',
    'Chicago Sun-Times', 'B', '"...the movie works."',
    'TRUE', 105, sysdate);

INSERT INTO MOVIE_REVIEWS_IN VALUES ('Mr. and Mrs. Smith', 'Christopher Null',
    'filmcritic.com', 'B+', '"...a funny and wild ride..."',
    'TRUE', 106, sysdate);

INSERT INTO MOVIE_REVIEWS_IN VALUES ('Bewitched', 'Roger Ebert',
    'Chicago Sun-Times', 'B-', '"...tolerably entertaining."',
    'TRUE', 107, sysdate);

INSERT INTO MOVIE_REVIEWS_IN VALUES ('Herbie: Fully Loaded', 'Roger Ebert',
    'Chicago Sun-Times', 'C', '"The movie is pretty cornball. Little kids would probably enjoy it, but their older brothers and sisters will be rolling their eyes..."',
    'TRUE', 108, sysdate);

INSERT INTO MOVIE_REVIEWS_IN VALUES ('Madagascar', 'Roger Ebert',
    'Chicago Sun-Times', 'B-', '"...an inessential but passably amusing animated comedy..."',
    'TRUE', 109, sysdate);

INSERT INTO MOVIE_REVIEWS_IN VALUES ('Rebound', 'Roger Ebert',
    'Chicago Sun-Times', 'C+', '"...fun enough in a sweet but predictable way."',
    'TRUE', 110, sysdate);

INSERT INTO MOVIE_REVIEWS_IN VALUES ('The Longest Yard', 'Roger Ebert',
    'Chicago Sun-Times', 'B', '"Most of its audiences will be satisfied enough when they leave the theater, although few will feel compelled to rent it on video to share with their friends."',
    'TRUE', 111, sysdate);

INSERT INTO MOVIE_REVIEWS_IN VALUES ('George A . Romero''s Land of the Dead', 'Roger Ebert',
    'Chicago Sun-Times', 'B', '"t''s good to see him (Romero) back in the genre he invented..."',
    'TRUE', 112, sysdate);

INSERT INTO MOVIE_REVIEWS_IN VALUES ('Star Wars: Episode III - Revenge of the Sith', 'Roger Ebert',
    'Chicago Sun-Times', 'A-', '"...a return to the classic space opera style that launched the series."',
    'TRUE', 113, sysdate);

INSERT INTO SEQUENCING_HELPER VALUES ('MOVIES_IN', 200, sysdate);


COMMIT;
