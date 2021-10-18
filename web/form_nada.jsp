<%-- 
    Document   : form_nada
    Created on : 16/10/2021, 22:25:21
    Author     : Danilo
--%>

<jsp:include page="header.jsp"/>
<div class="row">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
            <li class="breadcrumb-item text-sm"><a class="opacity-5 text-dark" href="index.jsp">Index</a></li>
            <li class="breadcrumb-item text-sm"><a class="opacity-5 text-dark" href="gerenciar_listarnada.do?acao=listarnada">Listar Nada</a></li>
            <li class="breadcrumb-item text-sm text-dark active" aria-current="page">Cadastrar Nada</li>
        </ol>
        <h6 class="font-weight-bolder mb-0 pb-2 ">Listar Nada</h6>
    </nav>
    <div class="col-12">
        <div class="card  mb-4">
            <div class="card-body px-0 pt-3 pb-2">
                <form action="" method="POST" class="px-3 pt-3">

                    <legend class="font-weight-bolder h6 text-center pb-1 mb-1"> <i class="fas fa-user-tie"></i>&nbsp;Dados Pessoais </legend>
                    <div class="form-group row">
                        <div class="form-group col-sm-4">
                            <label for="aluno_nome" class="control-label">Nome Aluno</label>
                            <input type="text" class="form-control" name="aluno_nome" id="aluno_nome" value="${aluno.aluno_nome}" maxlength="45" required>
                        </div>
                        <div class="form-group col-sm-4">
                            <label for="aluno_email" class="control-label">Email</label>
                            <input type="email" class="form-control" name="aluno_email" id="aluno_email" value="${aluno.aluno_email}" maxlength="45" required>
                        </div>
                        <div class="form-group col-sm-4">
                            <label for="aluno_telefone" class="control-label">Telefone</label>
                            <input type="text" class="form-control" name="aluno_telefone" id="aluno_telefone" value="${aluno.aluno_telefone}" maxlength="45">
                        </div>
                    </div>
                    
                    <legend class="font-weight-bolder h6 text-center pb-1 mb-1"> <i class="fas fa-map-marker-alt"></i>&nbsp;Endereço </legend>
                    <div class="form-group row">
                        <div class="form-group col-sm-2">
                            <label for="aluno_cep" class="control-label">CEP</label>
                            <input type="text" class="form-control" name="aluno_cep" id="aluno_cep" value="${aluno.aluno_cep}" maxlength="45">
                        </div>
                        <div class="form-group col-sm-6">
                            <label for="aluno_endereco" class="control-label">Endereço</label>
                            <input type="text" class="form-control" name="aluno_endereco" id="aluno_endereco" value="${aluno.aluno_endereco}" maxlength="45">
                        </div>
                        <div class="form-group col-sm-4">
                            <label for="aluno_complemento" class="control-label">Complemento</label>
                            <input type="text" class="form-control" name="aluno_complemento" id="aluno_complemento" value="${aluno.aluno_complemento}" maxlength="45">
                        </div>
                    </div>
                        
                    <legend class="font-weight-bolder h6 text-center pb-1 mb-1"> <i class="fas fa-tools"></i>&nbsp;Configurações </legend>
                    <div class="form-group row">
                        <div class="form-group col-sm-4">
                            <label for="aluno_cidade" class="control-label">Cidade</label>
                            <input type="text" class="form-control" name="aluno_cidade" id="aluno_cidade" value="${aluno.aluno_cidade}" maxlength="45">
                        </div>
                        <div class="form-group col-sm-2">
                            <label for="aluno_ativo" class="control-label">Ativo</label>
                            <select class="form-control" name="aluno_ativo">
                                <option value="1">Sim</option>
                                <option value="2">Não</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-6">
                            <label for="aluno_obs" class="control-label">Observações</label>
                            <textarea class="form-control" name="aluno_obs" id="aluno_obs" value="" maxlength="45">${aluno.aluno_obs}</textarea>
                        </div>
                    </div>

                    <input type="hidden" name="aluno_id" value="${aluno.aluno_id}">
                    <button class="btn btn-success" style="float: right; margin-left: 5px;">Salvar</button>
                    <a href="gerenciar_listarnada.do?acao=listarnada" class="btn btn-dark" style="float: right">Voltar</a>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
