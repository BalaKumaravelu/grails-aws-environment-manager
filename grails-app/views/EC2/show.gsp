<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'EC2.label', default: 'EC2')}" />
        <title>Show EC2 Instance</title>
    </head>
    <body>
        <a href="#show-EC2" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-EC2" class="content scaffold-show" role="main">
            <h1>Show EC2 Instance</h1>

            <table>
                <tr class="even">
                    <td>Name</td>
                    <td>${ec2.name}</td>
                </tr>
                <tr class="odd">
                    <td>Alias</td>
                    <td>${ec2.alias}</td>
                </tr>
                <tr class="even">
                    <td>Description</td>
                    <td>${ec2.description}</td>
                </tr>
                <tr class="odd">
                    <td>InstanceId</td>
                    <td>${ec2.instanceId}</td>
                </tr>
                <tr class="even">
                    <td>Status</td>
                    <td>${ec2.status}</td>
                </tr>
                <tr class="odd">
                    <td>Lane</td>
                    <td>${ec2.lane.name}</td>
                </tr>
            </table>

            <!--
            <ul>
                <li> <f:display bean="ec2" property="name"/></li>
                <li> <f:display bean="ec2" property="alias"/></li>
                <li> <f:display bean="ec2" property="description"/></li>
                <li> <f:display bean="ec2" property="instanceId"/></li>
                <li> <f:display bean="ec2" property="ipAddress"/></li>
                <li> <f:display bean="ec2" property="lane.name"/></li>
            </ul>
            -->

            <g:form resource="${this.EC2}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" id="${ec2.id}" resource="${this.EC2}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
