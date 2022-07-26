package br.thales.cinemov.Domain.Interface

import javax.security.auth.callback.Callback

interface IRepository<T>  {

     fun Criar(t : T)
     fun Get(uuid : String)
     fun Update(t : T)
     fun Excluir(t : T)
}