<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Backbone History</title>
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<link href="style/style.css" rel="stylesheet" />

<script src="js/jquery.js"></script>
<script src="js/underscore.js"></script>
<script src="js/backbone.js"></script>
<script src="js/bootstrap.js"></script>

<script type="text/template" id="terms">
            <table>
                <caption>Terms</caption>
                <thead>
                <tr>
                    <th>Term ID</th>
                    <th>Term Name</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
				{{ _.each(terms, function(term) { }}
                    <tr>
                        <td>{{= term.termName }}</td>
                        <td><a href="#editTerm/{{= term.termId }}">Edit</a> / <a href="#deleteTerm/{{= term.termId }}">Delete</a></td>
                    </tr>
                {{ }); }}
                </tbody>
            </table>
    </script>

<script type="text/template" id="addTerm">
         <div>
            <form>
                <div>
                    <label for="termName">Term Name</label> <textarea rows="1" id="termName"></textarea>
                </div>
                <button type="submit" id="add">Add</button>
            </form>
        </div>
    </script>

<script src="js/history.js"></script>
</head>
<body>
	<h1>Backbone Terms</h1>
	<br>
	<div>
		<div>
			<div>
				<ul>
					<li><a href="#terms">Terms</a></li>
					<li><a href="#addTerm">Add term</a></li>
				</ul>
			</div>
			<div>
				<div class="content"></div>
			</div>
		</div>
	</div>
</body>
<html>