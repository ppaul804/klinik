<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>KLINIK | ${titulo}</title>

        <!-- Custom fonts for this template-->
        <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="assets/css/sb-admin-2.min.css" rel="stylesheet">
        
        <!-- Custom styles for this page -->
        <link href="assets/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <jsp:include page="menu.jsp"/>
            
            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">
                    
                    <jsp:include page="topbar.jsp"/>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="gerenciar_index.do?acao=index">Home</a></li>
                        <li class="breadcrumb-item"><a href="gerenciar_perfil.do?acao=listar">Perfis</a></li>
                        <li class="breadcrumb-item active" aria-current="page">${titulo}</li>
                    </ol>
                </nav>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">

                    <div class="card-body">
                        <form class="pb-4" method="post" name="form_add" action="gerenciar_menu_perfil.do">

                            <fieldset class="mt-5 border p-2 mb-4">
                                <legend class="font-small form-control text-center text-primary"> <i class="fas fa-user-tie text-primary"></i>&nbsp;Dados Perfil/Menu </legend>

                                <div class="form-group row mb-4">
                                    <div class="col-md-12">
                                        <label><b>Perfil:</b> ${perfilVinculado.nome}</label>
                                    </div>
                                </div>
                                <div class="form-group row mb-4">
                                    <div class="col-md-12">
                                        <label>Menus</label>
                                        <select class="custom-select" name="idMenu" required="">
                                            <option value="${menu.exibir}" disabled="" selected="">Escolha...</option>
                                            <c:forEach var="menu" items="${perfilVinculado.naoMenus}">
                                                <option value="${menu.idMenu}">${menu.nome}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            

                                <input type="hidden" name="idPerfil" value="${perfilVinculado.idPerfil}">
                                <button type="submit" class="btn btn-primary btn-sm float-right ml-2"><i class="fas fa-save"></i>&nbsp;Vincular</button>
                            </fieldset>
                        </form>
                             
                        <h2 class="text-primary text-center">Menus Vinculados</h2>
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead class="text-primary">
                                <tr>
                                    <th>ID</th>
                                    <th>Nome Menu</th>
                                    <th>Link</th>
                                    <th>Ícone</th>
                                    <th>Exibir</th>
                                    <th class="text-right">Desvincular</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach var="menu" items="${perfilVinculado.menus}">
                                    <tr>
                                        <td>${menu.idMenu}</td>
                                        <td>${menu.nome}</td>
                                        <td>${menu.link}</td>
                                        <td>${menu.icone}</td>
                                        <td>
                                            <c:if test="${menu.exibir == 1}">
                                                Sim
                                            </c:if>
                                            <c:if test="${menu.exibir == 0}">
                                                Não
                                            </c:if>
                                        </td>
                                        <td  class="text-right">
                                            <a title="Excluir" href="javascript(void)" data-toggle="modal" data-target="#menu-${menu.idMenu}-${perfilVinculado.idPerfil}" class="btn btn sm btn-danger"> <i class="fas fa-times-circle fa-lg"></i> </a>
                                        </td>
                                    </tr>

                                    <div class="modal fade" id="menu-${menu.idMenu}-${perfilVinculado.idPerfil}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Tem certeza que deseja deletar?</h5>
                                                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">×</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">Você realmente deseja desvincular o Menu ${menu.nome}?</div>
                                                <div class="modal-footer">
                                                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Não</button>
                                                    <a class="btn btn-danger" href="gerenciar_menu_perfil.do?acao=desvincular&idMenu=${menu.idMenu}&idPerfil=${perfilVinculado.idPerfil}">Sim</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </tbody>
                        </table>
                        <a title="Voltar" href="gerenciar_perfil.do?acao=listar" class="btn btn-success btn-sm float-right mt-5"><i class="fas fa-arrow-left"></i>&nbsp;Voltar</a>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <jsp:include page="footer.jsp"/>

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="assets/vendor/jquery/jquery.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="assets/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="assets/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="assets/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="assets/js/demo/chart-area-demo.js"></script>
<script src="assets/js/demo/chart-pie-demo.js"></script>-->

<!-- Lista -->
<!-- Page level plugins -->
<script src="assets/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="assets/vendor/datatables/dataTables.bootstrap4.min.js"></script>
<script>
    $(document).ready(function () {
        $("#dataTable").dataTable({
            "bJQueryUI": true,
            "oLanguage": {
                "sProcessing": "Processando",
                "sLengthMenu": "Mostrar _MENU_ Registros",
                "sZeroRecords": "Não foram encontrados resultados",
                "sInfo": "Mostrar de _START_ até _END_ de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                "sInfoFiltered": "",
                "sInfoPostFix": "",
                "sSearch": "Pesquisar",
                "sUrl": "",
                "oPaginate": {
                    "sFirst": "Primeiro",
                    "sPrevious": "Anterior",
                    "sNext": "Próximo",
                    "sLast": "Último"
                }
            }
        });
    });
</script>
<!-- Page level custom scripts -->
<script src="assets/js/demo/datatables-demo.js"></script>

</body>

</html>

