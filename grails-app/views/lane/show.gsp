<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'lane.label', default: 'Lane')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-lane" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
         <h1>Show Lane</h1>
        <div id="show-lane" class="content scaffold-show" role="main">
            <table>
                <tr class="even">
                    <td>Name</td>
                    <td>${lane.name}</td>
                </tr>
                <tr class="odd">
                    <td>Alias</td>
                    <td>${lane.alias}</td>
                </tr>
                <tr class="even">
                    <td>Description</td>
                    <td>${lane.description}</td>
                </tr>
                 <tr class="odd">
                    <td>Status</td>
                    <td>${lane.status}</td>
                </tr>
                <tr class="even">
                    <td>EC2 Instances</td>
                    <td>
                        <g:each in="${lane.ec2s.sort{-it.id}.toList()}" status="i" var="ec2">
                            <li><a href="/EC2/show/${ec2.id}">${ec2.name}</a></li>
                        </g:each>
                    </td>
                </tr>
                <tr class="odd">
                    <td>Run History</td>
                    <td>
                        <g:each in="${lane.runs.sort{-it.id}.toList()}" status="k" var="run">
                            <li><a href="/run/show/${run.id}">
                                    <span style="color:green">[Start Time : ${run.startTime.format( 'MM-dd-yy  HH:mm:ss')}] - </span>
                                    <span style="color:red">[Stop Time : ${run.shutdownTime.format( 'MM-dd-yy  HH:mm:ss')}] - </span>
                                    <span style="color:green">[Start User : ${run.startUser}] - </span>
                                    <span style="color:red">[Stop User : ${run.shutdownUser}]</span>
                                </a>
                            </li>
                        </g:each>
                    </td>
                </tr>
            </table>

            <g:form  url="[controller: 'lane']" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.lane}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:hiddenField name="id" value="${lane.id}" />
                    <g:actionSubmit type="submit" value="Delete"  action="delete"/>
                    <g:actionSubmit value="Start"  action="start"/>
                    Choose Duration in hours
                    <g:select id="duration" name="duration" from="[1,2,3,4,6,8,10]" value="2"/>
                    <g:actionSubmit value="Increment"  action="increment"/>
                    <g:actionSubmit value="Shutdown"  action="shutdown"/>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
