SET SQL_SAFE_UPDATES = 0;

DELETE FROM workout;
DELETE FROM workoutIncludedTypes;
DELETE FROM workoutType;
DELETE FROM auditorium;
DELETE FROM period;
DELETE FROM registeredUser;
DELETE FROM loyaltyCard;
DELETE FROM workoutComment;

ALTER TABLE registeredUser
ADD UNIQUE INDEX(userName);

SET SQL_SAFE_UPDATES = 1;

INSERT INTO registeredUser(userId, userName, userPassword, userEmail, userFirstName, userLastName, userDateBirth, userAddress, userPhoneNumber,
	userDateTimeRegistration, userRole, isDeleted) VALUES
		(1, 'petar123', 'petar123', 'petarpetrovic@web.com', 'Petar', 'Petrovic', '1978-01-17', 'Bulevar Kneza Lazara 76, Beograd', '+381602222222', '2022-01-12 16:04:44', 'Administrator', false),
        (2, 'marina123', 'marina123', 'marinamarinkovic@web.com', 'Marina', 'Marinkovic', '1996-12-01', 'Svetog Save 3, Zvornik', '+38765300786', '2022-01-13 16:11:24', 'Administrator', false),
        (3, 'david123', 'david123', 'daviddavidovic@web.com', 'David', 'Davidovic', '1980-11-25', 'Mihajla Hamzica 1, Dubrovnik', '+385432774477', '2022-01-13 16:13:55', 'Administrator', true),
        (4, 'bojana123', 'bojana123', 'bojanabojanic@web.com', 'Bojana', 'Bojanic', '1966-10-10', 'Bulevar Kneza Lazara 44, Beograd', '+381644036473', '2022-01-13 17:02:55', 'Customer', false),
        (5, 'dragan123', 'dragan123', 'dragandragisic@web.com', 'Dragan', 'Dragisic', '1968-11-30', 'Franjevacka 29, Sarajevo', '+38766150390', '2022-01-13 17:10:22', 'Customer', false),
        (6, 'jovan123', 'jovan123', 'jovanjovanic@web.com', 'Jovan', 'Jovanic', '1963-10-02', 'Stojana Andrica 18, Nis', '+381636523462', '2022-01-13 17:16:20', 'Customer', false),
        (7, 'dejan123', 'dejan123', 'dejandejanic@web.com', 'Dejan', 'Dejanic', '1995-10-10', 'Maksut Sadikj 20, Skoplje', '+38942837141', '2022-01-13 17:20:11', 'Customer', false),
        (8, 'ivan123', 'ivan123', 'ivanivanovic@web.com', 'Ivan', 'Ivanovic', '1981-01-01', 'Vasa Raickovica 27, Podgorica', '+38260761683', '2022-01-13 17:33:41', 'Customer', true);

INSERT INTO loyaltyCard(userId, discount, points) VALUES
	(4, 5, 10),
    (7, 10, 5);

INSERT INTO workout(workoutId, workoutTypeName, workoutCoaches, workoutDescription, workoutPrice, workoutOrganizationType,
	workoutLevel, workoutLength, workoutAverageRate, isDeleted, workoutName, workoutImage) VALUES
        (1, 'Yoga, Step', 'jovana123', 'Yoga helps to relax better', 1200, 'Individual', 'Amateur', 120, NULL, false, 'Workout 1', 'images/yoga.png'),
		(2, 'Yoga', 'branko123/milan123', 'Yoga helps to relax better', 800, 'Group', 'Medium', 150, 8, false, 'Workout 2', 'images/yoga.png'),
        (3, 'Fitness', 'branko123', 'Fitness improves your health and mood', 1400, 'Individual', 'Advanced', 240, 9, false, 'Workout 3', 'images/fitness.png'),
        (4, 'Fitness, Cardio', 'jovana123/milan123', 'Fitness improves your health and mood', 1000, 'Group', 'Amateur', 120, NULL, false, 'Workout 4', 'images/fitness.png'),
        (5, 'Cardio', 'jovana123', 'Cardio training is the most effective way to remove fet layers, achieve and maintain condition', 1500, 'Individual', 'Advanced', 240, NULL, false, 'Workout 5', 'images/cardio.png'),
        (6, 'Cardio', 'jovana123/branko123', 'Cardio training is the most effective way to remove fet layers, achieve and maintain condition', 1100, 'Group', 'Amateur', 240, 9, false, 'Workout 6', 'images/cardio.png'),
        (7, 'Yoga', 'branko123', 'Yoga helps to relax better', 1300, 'Individual', 'Medium', 150, 10, true, 'Workout 7', 'images/yoga.png'),
        (8, 'Cardio', 'jovana123/branko123', 'Cardio training is the most effective way to remove fet layers, achieve and maintain condition', 1200, 'Group', 'Amateur', 120, NULL, true, 'Workout 8', 'images/cardio.png');

INSERT INTO workoutIncludedTypes(workoutId, workoutTypeName) VALUES
    (1, 'Yoga'), (1, 'Step'), (2, 'Yoga'), (3, 'Fitness'), (4, 'Fitness'), (4, 'Cardio'), (5, 'Cardio'), (6, 'Cardio'),
    (7, 'Yoga'), (8, 'Cardio');


INSERT INTO workoutType(workoutTypeName, workoutTypeDetailedDescription) VALUES
	('Yoga', 'Yoga is the science of body, mind and spirit. The physical part of Yoga consists of Asanas - exercises and postures that develop the body and Pranayama - breathing exercises. 
    Mental development in Yoga is achieved through breathing exercises, concentration exercises and meditation. The physical benefits you can expect from exercise are, among others, 
    improved circulation, increased elasticity of all muscles, greater strength and resistance to disease. Yoga pays special attention to the spinal column, whose increased elasticity not 
    only results in good health, but also successfully combats the many difficulties that people in the 21st century suffer from. Asanas stretch, massage and stimulate not only muscles, 
    but also internal organs and glandular system.'),
    ('Fitness', 'Fitness improves your health and mood. Fitness includes several training programs and the biggest advantage of which is adaptability to the individual needs of each 
    individual. The fitness club is equipped with the most modern equipment from the leading manufacturers'),
    ('Cardio', 'Cardio training is the most effective way to remove fat deposits, achieve and maintain fitness. Cardio exercises are necessary for any effective training program because they 
    stimulate fat burning, but they are also good for the overall health of the body. Also, if you have not exercised for a long time, and you do not have the strength for other workouts, 
    you can always adjust the cardio workout to your abilities.'),
    ('Pilates', 'Pilates largely avoids high impact, high power output, and heavy muscular and skeletal loading. Builds strength without extra effort, creating a slender body with tight 
    thighs and a flat stomach. He teaches the body good posture and simple, graceful movements. Pilates largely avoids high impact, high power output, and heavy muscular and skeletal 
    loading. It can even help relieve back pain. Professional dancers have been using the benefits of Pilates for decades. Top athletes use it for strength, flexibility and injury 
    prevention. Hollywood stars and supermodels use it to maintain a good look. Miracle? Not really ... Developed from, above all, rehabilitation techniques, Pilates is a safe, subtle 
    system of exercises that uses mats or appropriate equipment to help you look and feel your best. Regardless of your age or fitness, Pilates will suit you.'),
    ('Step', 'Step aerobics is a choreographic training with a lot of steps and movements, which also gives a form of dance, but the platform for "climbing" is used all the time. The 
    platform can be adjusted to several different heights, which allows practitioners to work with different training. The training consists of several types of steps that can be combined 
    with the work of the hands and with an additional small load of two-handed weights, so in addition to endurance and calorie burning, we also work on muscle tone. There is great 
    interest in this type of aerobics, which bodybuilders use as cardio training.');

INSERT INTO auditorium(auditoriumId, capacity, isDeleted) VALUES 
	('100', 5, false),
    ('101', 10, false),
    ('102', 7, false),
    ('103', 8, false),
    ('104', 4, false),
    ('105', 4, false),
    ('106', 3, false);
    
INSERT INTO period(periodId, auditoriumId, workoutId, workoutDateTimeStart, workoutDateTimeEnd) VALUES
    (1, '100', '1', '2022-01-13 19:00:00', '2022-01-13 21:00:00'),
    (2, '101', '2', '2022-01-13 14:00:00', '2022-01-13 16:30:00'),
    (3, '102', '3', '2022-01-13 23:00:00', '2022-01-14 01:00:00'),
    (4, '102', '4', '2022-01-15 17:30:00', '2022-01-15 19:30:00'),
    (5, '103', '5', '2022-01-18 11:00:00', '2022-01-18 15:00:00'),
    (6, '105', '6', '2022-01-19 07:30:00', '2022-01-19 11:30:00'),
	(7, '103', '7', '2022-01-23 18:30:00', '2022-01-23 21:00:00'),
    (8, '105', '8', '2022-01-27 06:30:00', '2022-01-27 08:30:00');
    
INSERT INTO periodReserved(periodId, userId) VALUES
	(1, 4),
    (2, 4),
    (2, 4),
    (2, 5),
    (3, 5),
    (4, 4);
    
INSERT INTO workoutComment(workoutId, commentAuthorId, commentContent, commentAverageRate, commentDateTimePosted, commentAuthorName, commentStatus) VALUES
    (2, 4, 'Sve kao sto sam i ocekivao', 9, '2022-01-15 19:38:23', 'bojana123', 'Approved'),
    (2, 6, 'Neke stvari mogu da se unaprede u vezi ovog termina', NULL, '2021-01-19 13:23:33', 'jovan123', 'OnWaiting'),
    (7, 4, 'Termin uopste nije bio u skladu sa mojim ocekivanjima', NULL, '2022-01-18 20:00:12', 'bojana123', 'Declined'),
    (5, 7, 'Usluga na nivou', NULL, '2022-01-20 00:00:12', 'dejan123', 'OnWaiting');

    
    