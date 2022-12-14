<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'EC2.label', default: 'EC2')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-EC2" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-EC2" class="content scaffold-create" role="main">
            <h1>Create EC2</h1>



            <g:form resource="${this.EC2}" method="POST">

                <fieldset class="form">
                    <f:field bean="EC2" property="name"/>
                    <f:field bean="EC2" property="alias"/>
                    <f:field bean="EC2" property="description"/>
                    <f:field bean="EC2" property="instanceId"/>
                    <f:field bean="EC2" property="ipAddress"/>
                    <f:field bean="EC2" property="lane" widget-optionValue="name"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
