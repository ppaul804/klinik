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

        <!-- Custom fonts for this template -->
        <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template -->
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

                        <!-- Page Heading -->
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="gerenciar_index.do?acao=index">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">${titulo}</li>
                            </ol>
                        </nav>

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary float-left pt-1">Clientes Cadastrados</h6>
                                <a href="gerenciar_cliente.do?acao=cadastrar" class="btn btn-success btn-sm float-right" ><i class="fas fa-user-plus"></i>&nbsp;Novo</a>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead class="text-primary">
                                            <tr>
                                                <th>ID</th>
                                                <th>Nome</th>
                                                <th>Email</th>
                                                <th>Telefone</th>
                                                <th>CPF</th>
                                                <th class="text-right">Ação</th>
                                            </tr>
                                        </thead>
                                        <jsp:useBean class="model.ClienteDAO" id="cDAO" />
                                        <tbody>
                                             <c:forEach var="c" items="${cDAO.lista}">
                                            <tr>
                                                
                                                <td>${c.idCliente}</td>
                                                <td>${c.nome}</td>
                                                <td>${c.email}</td>
                                                <td>${c.telefone}</td>
                                                <td>${c.cpf}</td>
                                                <td  class="text-right">
                                                    <a title="Cadastrar Consulta" href="#" class="btn btn sm btn-info"> <i class="far fa-address-book"></i> </a>
                                                    <a title="Cadastrar Contrato" href="gerenciar_contrato.do?acao=cadastrar&idCliente=${c.idCliente}" class="btn btn sm btn-dark"> <i class="fas fa-file-signature"></i> </a>
                                                    <a title="Editar" href="gerenciar_cliente.do?acao=alterar&idCliente=${c.idCliente}" class="btn btn sm btn-primary"> <i class="fas fa-user-edit"></i> </a>
                                                    <a title="Excluir" href="javascript(void)" data-toggle="modal" data-target="#cliente-${c.idCliente}" class="btn btn sm btn-danger"> <i class="fas fa-user-times"></i> </a>
                                                </td>
                                            </tr>
                                        <div class="modal fade" id="cliente-${c.idCliente}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">Tem certeza que deseja deletar?</h5>
                                                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">×</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">Você realmente deseja deletar o Cliente ${c.nome}?</div>
                                                    <div class="modal-footer">
                                                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Não</button>
                                                        <a class="btn btn-danger" href="gerenciar_cliente.do?acao=deletar&cliente_id=${c.idCliente}">Sim</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                             </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
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

        <!-- Bootstrap core JavaScript-->
        <script src="assets/vendor/jquery/jquery.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="assets/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="assets/js/sb-admin-2.min.js"></script>

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
