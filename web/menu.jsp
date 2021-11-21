<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">KLINIK</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item <c:if test="${!empty(activeI)}">active</c:if>">
            <a class="nav-link" href="gerenciar_index.do?acao=index">
                <i class="fas fa-home"></i>
                <span>Home</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Modulos
        </div>

        <!-- Nav Item - Tables -->
        <li class="nav-item <c:if test="${!empty(activeU)}">active</c:if>">
        <a class="nav-link" href="gerenciar_usuarios.do?acao=listar">
            <i class="fas fa-users"></i>
            <span>Usuario</span></a>
        <a class="nav-link" href="listar_cliente.jsp">
            <i class="fas fa fa-child"></i>
            <span>Clientes</span>
        </a>
        <a class="nav-link" href="listar_menu.jsp">
            <i class="fas fa fa-list"></i>
            <span>Gerenciar Menu</span>
        </a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->
