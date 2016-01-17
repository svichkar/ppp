<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Admin Page">
    <jsp:attribute name="content_area">
<div class="divTable">

        <table>
        <tr>
        <td width="1%">COMMANDS</td>
        <td width="5%">USER ID</td>
        <td width="18%">FIRST NAME</td>
        <td width="18%">LAST NAME</td>
        <td width="15%">LOGIN</td>
        <td width="15%">PASSWORD</td>
        <td width="20%">E-MAIL</td>
        <td width="7%">ROLE</td>
        </tr>

        <form action="admin" method="post">
        <tr class="rows">
        <td>
        <input type="submit" name="operation" value="update" style="width:100%;color:#fffff;background-color: #e5ffff;"/>
        <input type="submit" name="operation" value="delete" style="width:100%;color:#fffff;background-color: #e5ffff;"/>
        </td>
        <td><input type="text" name="id" value="1" readonly/></td>
        <td><input type="text" name="fisrt_name" value="Konstantin" maxlength="30" pattern="[A-Za-z]{3,30}" required/></td>
        <td><input type="text" name="last_name" value="Svichkar" maxlength="30" pattern="[A-Za-z]{3,30}" required/></td>
        <td><input type="text" name="login" value="svichkar" maxlength="20" pattern="[^?-??-???]{3,20}" required/></td>
        <td><input type="text" name="pass" value="123456789" maxlength="20" pattern="[^?-??-???]{3,20}" required/></td>
        <td><input type="text" name="email" value="2012kostyan@gmail.com" maxlength="50" pattern="\S+@[a-z]+.[a-z]+" required/></td>
        <td>
        <select name="role" required style="width:100%;border: none;">
        <option selected>admin</option>
        <option>guest</option>
        </select>
        </td>
        </tr>
        </form>

        <form action="admin" method="post">
        <tr class="rows">
        <td>
        <input type="submit" name="operation" value="update" style="width:100%;color:#fffff;background-color: #e5ffff;"/>
        <input type="submit" name="operation" value="delete" style="width:100%;color:#fffff;background-color: #e5ffff;"/>
        </td>
        <td><input type="text" name="id" value="2" readonly/></td>
        <td><input type="text" name="fisrt_name" value="Admin" maxlength="30" pattern="[A-Za-z]{3,30}" required/></td>
        <td><input type="text" name="last_name" value="Adminovich" maxlength="30" pattern="[A-Za-z]{3,30}" required/></td>
        <td><input type="text" name="login" value="kos" maxlength="20" pattern="[^?-??-???]{3,20}" required/></td>
        <td><input type="text" name="pass" value="123" maxlength="20" pattern="[^?-??-???]{3,20}" required/></td>
        <td><input type="text" name="email" value="admin@gmail.com" maxlength="50" pattern="\S+@[a-z]+.[a-z]+" required/></td>
        <td>
        <select name="role" required style="width:100%;border: none;">
        <option selected>admin</option>
        <option>guest</option>
        </select>
        </td></tr>
        </form>
        <form action="admin" method="post">
                <tr class="rows">
        <td>
        <input type="submit" name="operation" value="update" style="width:100%;color:#fffff;background-color: #e5ffff;"/>
        <input type="submit" name="operation" value="delete" style="width:100%;color:#fffff;background-color: #e5ffff;"/>
        </td>
        <td><input type="text" name="id" value="3" readonly/></td>
        <td><input type="text" name="fisrt_name" value="Guest" maxlength="30" pattern="[A-Za-z]{3,30}" required/></td>
        <td><input type="text" name="last_name" value="Guestovich" maxlength="30" pattern="[A-Za-z]{3,30}" required/></td>
        <td><input type="text" name="login" value="guest" maxlength="20" pattern="[^?-??-???]{3,20}" required/></td>
        <td><input type="text" name="pass" value="321" maxlength="20" pattern="[^?-??-???]{3,20}" required/></td>
        <td><input type="text" name="email" value="guest@gmail.com" maxlength="50" pattern="\S+@[a-z]+.[a-z]+" required/></td>
        <td>
        <select name="role" required style="width:100%;border: none;">
        <option>admin</option>
        <option selected>guest</option>
        </select>
        </td></tr>
        </form>
        <form action="admin" method="post">
                <tr class="rows">
        <td>
        <input type="submit" name="operation" value="update" style="width:100%;color:#fffff;background-color: #e5ffff;"/>
        <input type="submit" name="operation" value="delete" style="width:100%;color:#fffff;background-color: #e5ffff;"/>
        </td>
        <td><input type="text" name="id" value="4" readonly/></td>
        <td><input type="text" name="fisrt_name" value="Another" maxlength="30" pattern="[A-Za-z]{3,30}" required/></td>
        <td><input type="text" name="last_name" value="One" maxlength="30" pattern="[A-Za-z]{3,30}" required/></td>
        <td><input type="text" name="login" value="one" maxlength="20" pattern="[^?-??-???]{3,20}" required/></td>
        <td><input type="text" name="pass" value="111" maxlength="20" pattern="[^?-??-???]{3,20}" required/></td>
        <td><input type="text" name="email" value="one@gmail.com" maxlength="50" pattern="\S+@[a-z]+.[a-z]+" required/></td>
        <td>
        <select name="role" required style="width:100%;border: none;">
        <option>admin</option>
        <option selected>guest</option>
        </select>
        </td></tr>
        </form>
        <form action="admin" method="post">
                <tr class="rows">
        <td>
        <input type="submit" name="operation" value="update" style="width:100%;color:#fffff;background-color: #e5ffff;"/>
        <input type="submit" name="operation" value="delete" style="width:100%;color:#fffff;background-color: #e5ffff;"/>
        </td>
        <td><input type="text" name="id" value="5" readonly/></td>
        <td><input type="text" name="fisrt_name" value="Admin" maxlength="30" pattern="[A-Za-z]{3,30}" required/></td>
        <td><input type="text" name="last_name" value="Second" maxlength="30" pattern="[A-Za-z]{3,30}" required/></td>
        <td><input type="text" name="login" value="admin" maxlength="20" pattern="[^?-??-???]{3,20}" required/></td>
        <td><input type="text" name="pass" value="12345" maxlength="20" pattern="[^?-??-???]{3,20}" required/></td>
        <td><input type="text" name="email" value="admin2@gmail.com" maxlength="50" pattern="\S+@[a-z]+.[a-z]+" required/></td>
        <td>
        <select name="role" required style="width:100%;border: none;">
        <option selected>admin</option>
        <option>guest</option>
        </select>
        </td></tr>
        </form>

        <form action="admin" method="post">
        <tr class="rows">
        <td>
        <input type="submit" name="operation" value="add" style="width:100%;height:100%;color:#fffff;background-color: #e5ffff;"/>
        </td>
        <td><input type="text" name="id" hidden placeholder="id"></input></td>
        <td><input type="text" name="fisrt_name" pattern="[A-Za-z]{3,30}" required placeholder="first name"></input></td>
        <td><input type="text" name="last_name" pattern="[A-Za-z]{3,30}" required placeholder="second name"></input></td>
        <td><input type="text" name="login" pattern="[^?-??-???]{3,20}" required placeholder="login"></input></td>
        <td><input type="text" name="pass" pattern="[^?-??-???]{3,20}" required placeholder="password"></input></td>
        <td><input type="text" name="email" pattern="\S+@[a-z]+.[a-z]+" required placeholder="e-mail"></input></td><td>

        <select name="role" required style="width:100%;border: none;">
        <option selected disabled></option>
        <option>admin</option>
        <option>guest</option>
        </select>
        </tr>
        </form>

        </table>
</div>

    </jsp:attribute>
</t:general_template>