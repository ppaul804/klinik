<%@page import="model.Usuario"%>
<%@page import="controller.GerenciarLogin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ContratoServico"%>
<%@page import="model.ClienteDAO"%>
<%@page import="model.Cliente"%>
<%@page import="model.Contrato"%>
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

        <title>KLINIK | Finalizar Contrato</title>

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
                        <li class="breadcrumb-item"><a href="gerenciar_cliente.do?acao=listar">Clientes</a></li>
                        <li class="breadcrumb-item"><a href="form_contrato.jsp?acao=c">Novo Contrato</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Finalizar Contrato</li>
                    </ol>
                </nav>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">

                    <div class="card-body">
                                         
                        <%
                            
                            Usuario usuarioLogado = GerenciarLogin.verificarAcesso(request, response);
                            request.setAttribute("usuarioLogado", usuarioLogado);
                            
                            Contrato con = new Contrato();
                            Cliente cliente = new Cliente();
                            try {
                                    
                                con = (Contrato) session.getAttribute("contrato");
                                
                            } catch (Exception e) {
                                out.println("Erro: "+ e);
                            }
                        %>
                        <h2 class="text-primary text-center mb-4">Serviços do Contrato</h2>
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead class="text-primary">
                                <tr>
                                    <th>Item</th>
                                    <th>Serviço</th>
                                    <th>Quantidade</th>
                                    <th>Valor</th>
                                    <th>Total</th>
                                    <th class="text-right">Ação</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    double total = 0;
                                    int cont = 0;
                                    for (ContratoServico contratoServico:con.getCarrinho()) {
                                        
                                %>
                                <tr>
                                    <td class="text-center"> <%= cont+1 %> </td>
                                    <td> <%= contratoServico.getServico().getNome() %> </td>
                                    <td> <%= contratoServico.getQuantidade() %> </td>
                                    <td>R$ <%= contratoServico.getValor() %> </td>
                                    <td>R$ <%= contratoServico.getQuantidade() * contratoServico.getValor() %> </td>
                                    <td class="text-right">
                                        <a title="Excluir" href="javascript(void)" data-toggle="modal" data-target="#contratoServico-<%= cont %>-<%= cont+1 %>" class="btn btn sm btn-danger"> <i class="fas fa-times-circle fa-lg"></i> </a>
                                    </td>
                                </tr>
                                <div class="modal fade" id="contratoServico-<%= cont %>-<%= cont+1 %>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Tem certeza que deseja excluir?</h5>
                                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">Você realmente deseja excluir o Item <%= cont+1 %>?</div>
                                            <div class="modal-footer">
                                                <button class="btn btn-secondary" type="button" data-dismiss="modal">Não</button>
                                                <a class="btn btn-danger" href="gerenciar_carrinho.do?acao=deletar&index=<%= cont %>">Sim</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%
                                        total = total + (contratoServico.getQuantidade() * contratoServico.getValor());
                                        cont++;
                                    }
                                %>
                            </tbody>
                        </table>
                        
                        <form action="gerenciar_contrato.do" method="post">
                            <fieldset class="mt-2 border p-2 mb-4">
                                <legend class="font-small form-control text-center text-primary"> <i class="fas fa-file-signature text-primary"></i>&nbsp;Dados Contrato </legend>
                                <div class="form-group row mb-4">
                                    <div class="col-md-4">
                                        <h5 class=""><i class="fas fa-user-tie"></i> <strong>Atendente:</strong> <%=con.getAtendente().getNome()%></h5>
                        
                                        <h5><i class="fas fa-user-tag"></i> <strong>Cliente:</strong> <%=con.getIdCliente().getNome()%></h5>
                                    </div>
                                    <div class="col-md-3">
                                        <label>Data Contrato</label>
                                        <input type="datetime-local" class="form-control" name="data_contrato" value="" required=""> 
                                    </div>
                                    <div class="col-md-3">
                                        <label>Status</label>
                                        <select class="custom-select" name="status" required="">
                                            <option value="" disabled="">Escolha um Status</option>
                                            <option value="cancelado" >Cancelado</option>
                                            <option value="aberto" selected="">Em Aberto</option>
                                            <option value="finalizado" >Finalizado</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <label class="text-center">Valor Total (R$)</label>
                                        <h4 class="text-primary">R$ <%= total %> </h4>
                                    </div>
                                </div>
                                
                                <button type="submit" class="btn btn-primary btn-sm float-right ml-2"><i class="fas fa-save"></i>&nbsp;Finalizar Contrato</button>
                            </fieldset>
                        </form>
                                
                        <a title="Continuar Contrato" href="form_contrato.jsp?acao=c" class="btn btn-info btn-sm float-right ml-2"><i class="fas fa-save"></i>&nbsp;Continuar Contrato</a>
                        <a title="Voltar" href="gerenciar_cliente.do?acao=listar" class="btn btn-success btn-sm float-right"><i class="fas fa-arrow-left"></i>&nbsp;Cancelar</a>                  
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
<script src="assets/js/demo/chart-pie-demo.js"></script>

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

