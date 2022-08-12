<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'EC2.label', default: 'EC2')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-EC2" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-EC2" class="content scaffold-list" role="main">
            <h1>EC2 Instances</h1>
            <f:table collection="${ec2List}" properties="['name', 'alias',  'description', 'lane.name', 'ipAddress', 'status', 'id']"/>
            <!--
            <table>
              <thead>
                <tr>
                  <g:sortableColumn property="name" title="Name"/>
                  <g:sortableColumn property="alias" title="Alias"/>
                  <g:sortableColumn property="lane" title="Lane"/>
                   <g:sortableColumn property="description" title="Description"/>
                  <g:sortableColumn property="instancId" title="Instance Id"/>
                  <g:sortableColumn property="ipAddress" title="IpAddress"/>
                  <g:sortableColumn property="status" title="Status"/>
                </tr>
              </thead
              <tbody>
                <g:each in="${ec2List}" status="i" var="ec2">
                  <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td><a href="/EC2/show/${ec2.id}"> ${ec2.name}</a></td>
                    <td>${ec2.alias}</td>
                    <td>${ec2.lane.name}</td>
                    <td>${ec2.description}</td>
                    <td>${ec2.instanceId}</td>
                    <td>${ec2.ipAddress}</td>
                    <td>${ec2.status}</td>                       
                  </tr>
                </g:each>
              </tbody>
            </table>
            -->

            <!--
            <div class="pagination">
                <g:paginate total="${ec2List.count ?: 0}" />
            </div>
            -->
        </div>
    </body>
</html>