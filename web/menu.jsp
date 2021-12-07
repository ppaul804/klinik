<%@page import="model.Usuario"%>
<%@page import="controller.GerenciarLogin"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Usuario usuarioLogado = GerenciarLogin.verificarAcesso(request, response);
    request.setAttribute("usuarioLogado", usuarioLogado);
    //<i class="fas fa-laugh-wink"></i>
    //<div class="sidebar-brand-text mx-3">KLINIK</div>
%>
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <img src="assets/img/KlinikIconeAlbino.png" style="padding: 10px 20px 10px 20px">
    
    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <!-- Heading -->
        <div class="sidebar-heading mt-3 text-center">
            Home-Page
        </div>
    
        <c:if test="${usuarioLogado != null && usuarioLogado.idPerfil != null}">
            <c:forEach var="menu" items="${usuarioLogado.idPerfil.menus}">
                <c:if test="${menu.nome == 'Página Inicial'}">
                    <li class="nav-item">
                        <a class="nav-link" href="${menu.link}">
                            <i class="${menu.icone}"></i>
                            <span>${menu.nome}</span>
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </c:if>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading text-center">
            Modulos
        </div>
                    
        <c:if test="${usuarioLogado != null && usuarioLogado.idPerfil != null}">
            <c:forEach var="menu" items="${usuarioLogado.idPerfil.menus}">
                <c:if test="${menu.nome == 'Clientes'}">
                    <!-- Nav Item - Tables -->
                    <li class="nav-item">
                        <a class="nav-link" href="${menu.link}">
                            <i class="${menu.icone}"></i>
                            <span>${menu.nome}</span>
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </c:if>
                    
        <c:if test="${usuarioLogado != null && usuarioLogado.idPerfil != null}">
            <c:forEach var="menu" items="${usuarioLogado.idPerfil.menus}">
                <c:if test="${menu.nome == 'Contratos'}">
                    <!-- Nav Item - Tables -->
                    <li class="nav-item">
                        <a class="nav-link" href="${menu.link}">
                            <i class="${menu.icone}"></i>
                            <span>${menu.nome}</span>
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </c:if>
                    
        <c:if test="${usuarioLogado != null && usuarioLogado.idPerfil != null}">
            <c:forEach var="menu" items="${usuarioLogado.idPerfil.menus}">
                <c:if test="${menu.nome == 'Consultas'}">
                    <!-- Nav Item - Tables -->
                    <li class="nav-item">
                        <a class="nav-link" href="${menu.link}">
                            <i class="${menu.icone}"></i>
                            <span>${menu.nome}</span>
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </c:if>
                    
        <c:if test="${usuarioLogado != null && usuarioLogado.idPerfil != null}">
            <c:forEach var="menu" items="${usuarioLogado.idPerfil.menus}">
                <c:if test="${menu.nome == 'Serviços'}">
                    <!-- Nav Item - Tables -->
                    <li class="nav-item">
                        <a class="nav-link" href="${menu.link}">
                            <i class="${menu.icone}"></i>
                            <span>${menu.nome}</span>
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </c:if>

        <c:if test="${usuarioLogado != null && usuarioLogado.idPerfil != null}">
            <c:if test="${usuarioLogado.idPerfil.nome != 'Atendente' && usuarioLogado.idPerfil.nome != 'Dentista'}">
                <!-- Divider -->
                <hr class="sidebar-divider">
                
                <!-- Heading -->
                <div class="sidebar-heading text-center">
                    Configurações
                </div>
            </c:if>
        </c:if>
        
        <c:if test="${usuarioLogado != null && usuarioLogado.idPerfil != null}">
            <c:forEach var="menu" items="${usuarioLogado.idPerfil.menus}">
                <c:if test="${menu.nome == 'Usuarios'}">
                    <!-- Nav Item - Tables -->
                    <li class="nav-item">
                        <a class="nav-link" href="${menu.link}">
                            <i class="${menu.icone}"></i>
                            <span>${menu.nome}</span>
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </c:if>
        
        <c:if test="${usuarioLogado != null && usuarioLogado.idPerfil != null}">
            <c:forEach var="menu" items="${usuarioLogado.idPerfil.menus}">
                <c:if test="${menu.nome == 'Menus'}">
                    <!-- Nav Item - Tables -->
                    <li class="nav-item">
                        <a class="nav-link" href="${menu.link}">
                            <i class="${menu.icone}"></i>
                            <span>${menu.nome}</span>
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </c:if>
        
        <c:if test="${usuarioLogado != null && usuarioLogado.idPerfil != null}">
            <c:forEach var="menu" items="${usuarioLogado.idPerfil.menus}">
                <c:if test="${menu.nome == 'Perfil'}">
                    <!-- Nav Item - Tables -->
                    <li class="nav-item">
                        <a class="nav-link" href="${menu.link}">
                            <i class="${menu.icone}"></i>
                            <span>${menu.nome}</span>
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </c:if>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->
