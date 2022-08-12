<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'lane.label', default: 'Lane')}" />
        <title>Lane List</title>
    </head>
    <body>
        <a href="#list-lane" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-lane" class="content scaffold-list" role="main">
            <h1>Lane List</h1>
            <!--
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>

                <f:table collection="${laneList}"  properties="['name', 'alias', 'description', 'ec2s', 'runs']" />

                <div class="pagination">
                    <g:paginate total="${laneCount ?: 0}" />
                </div>
            -->
            <!--
            <f:table collection="${laneList}" properties="['name', 'alias',  'description', 'ec2s', 'runs', 'id']"/>
            -->

            <table>
                <thead>
                    <tr>
                        <g:sortableColumn property="name" title="Name"/>
                        <g:sortableColumn property="alias" title="Alias"/>
                        <g:sortableColumn property="description" title="Description"/>
                        <g:sortableColumn property="status" title="Status"/>
                        <g:sortableColumn property="ec2s" title="EC2s"/>
                        <g:sortableColumn property="runHistorys" title="Run History"/>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${laneList}" status="i" var="lane">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td><a href="/lane/show/${lane.id}"> ${lane.name}</a></td>
                            <td>${lane.alias}</td>
                            <td>${lane.description}</td>
                            <td>${lane.status}</td>
                            <td>
                                <g:each in="${lane.ec2s.sort{-it.id}.toList()}" status="j" var="ec2">
                                    <li>
                                        <a href="/EC2/show/${ec2.id}">${ec2.name}</a>
                                    </li>
                                </g:each>
                            </td>
                            <td>
                                <g:if test="${lane.runs.toList().size() != 0}">
                                     <span style="color:green">[Start Time : ${lane.runs.sort{-it.id}.toList().first().startTime.format( 'MM-dd-yy  HH:mm')} ] - </span>
                                     <span style="color:red"> [Stop Time : ${lane.runs?.sort{-it.id}.toList().first().shutdownTime.format( 'MM-dd-yy  HH:mm')}] - </span>
                                     <span style="color:green">[Start User : ${lane.runs?.sort{-it.id}.toList().first().startUser} ] - </span>
                                     <span style="color:red"> [Stop User : ${lane.runs?.sort{-it.id}.toList().first().shutdownUser}]</span>
                                </g:if>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>


            <!--
                <div class="pagination">
                    <g:paginate total="${laneCount ?: 0}" />
                </div>
            -->
        </div>
    </body>
</html>