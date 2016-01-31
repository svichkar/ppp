<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<form action="j_spring_security_check" method="POST">
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="j_username" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="j_password" /></td>
            </tr>
            <tr>
                <td colspan="2" align="right"><input type="submit" value="Login"/></td>
            </tr>
        </table>
    </form>
    <a href="main">MainPage</a>