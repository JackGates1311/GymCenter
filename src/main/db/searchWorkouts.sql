SELECT * FROM workout
WHERE workoutName LIKE '%%' AND workoutCoaches LIKE '%%' AND (workoutPrice BETWEEN 400 AND 1400) AND workoutId LIKE '%(SELECT workoutId FROM workoutIncludedTypes WHERE workoutTypeName LIKE '%Yoga%')%' AND
workoutOrganizationType LIKE '%%' AND workoutLevel LIKE '%%'
ORDER BY workoutName DESC