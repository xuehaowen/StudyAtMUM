<?php include("top.html"); ?>

<!-- CSE 190 M, Homework 4 (NerdLuv)
     This provided file is the front page that links to two of the files you are going
     to write, signup.php and matches.php.  You don't need to modify this file. -->
<div>
    <form>
        <fieldset>
            <legend>New User Signup:</legend>
            <table>
                <tbody>
                    <tr>
                        <td><label>Name:</label></td>
                        <td><input name="name" type="text" maxlength="16"/></td>
                    </tr>
                    <tr>
                        <td><label>Gender:</label></td>
                        <td>
                            <input name='gender' type="radio" value="Male"/>Male
                            <input name='gender' type="radio" value="Female" checked/>Female
                        </td>
                    </tr>
                    <tr>
                        <td><label>Age:</label></td>
                        <td><input class="ipt_short" name='age' type="text" maxlength="2"/></td>
                    </tr>
                    <tr>
                        <td><label>Personality type:</label></td>
                        <td><input class="ipt_short" name='pt' type="text"/><a href="http://www.humanmetrics.com/cgi-win/JTypes2.asp">(Don't know your type?)</a></td>
                    </tr>
                    <tr>
                        <td><label>Favorite OS</label></td>
                        <td>
                            <select name="favoriteOS">
                                <option selected>Windows</option>
                                <option>Linux</option>
                                <option>Mac OS X</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Seeking age:</label></td>
                        <td>
                            <input class="ipt_short" name='min' type="text" placeholder="min" maxlength="2"/>
                            to
                            <input class="ipt_short" name='max' type="text" placeholder="max" maxlength="2"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Sign Up"/>
        </fieldset>
    </form>
</div>

<?php include("bottom.html"); ?>
