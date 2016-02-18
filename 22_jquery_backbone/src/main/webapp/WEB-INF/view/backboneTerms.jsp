<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Backbone Terms</title>
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
						<td>{{= term.termId }}</td>
                        <td>{{= term.termName }}</td>
                        <td><a href="#editTerm/{{= term.termId }}">Edit</a> / <a href="#deleteTerm/{{= term.termId }}">Delete</a></td>
                    </tr>
                {{ }); }}
                </tbody>
            </table>
    </script>



<script type="text/template" id="addTerm">
            	<form>
                	<input type="hidden" id="termId" value="0">
					<table>
                	<tr>
	                    <td><label for="termName">Term Name</label></td> 
						<td><textarea class="form-control" rows="1" id="termName"></textarea></td>
    	            </tr>
					<tr>
                	<td colspan=2><button type="submit" id="add">Add</button></td>
					</tr>
					</table>
            	</form>
    	</script>

<script type="text/template" id="editTerm">
        <div class="" data-example-id="basic-forms">
            <form>
                <input type="hidden" id="termId" value="{{= term.termId }}">
					<table>
                	<tr>
	                    <td><label for="termName">Term Name</label></td> 
						<td><textarea class="form-control" rows="1" id="termName">{{= term.termName }}</textarea></td>
    	            </tr>
					<tr>
                	<td colspan=2><button type="submit" id="update">Update</button></td>
					</tr>
					</table>
            </form>
        </div>
    </script>

<script src="js/backboneTerms.js"></script>
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