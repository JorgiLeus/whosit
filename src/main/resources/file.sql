SELECT g.staff_id, g.score, min(g.completionTime) as completionTime
FROM whosit.game g
INNER JOIN (
SELECT game.staff_id, max (game.score) as maxScore
from whosit.game game
GROUP BY game.staff_id
  ) ga on g.staff_id = ga.staff_id AND g.score = ga.maxScore
group By g.staff_id, g.score
Order By g.score desc, completionTime asc