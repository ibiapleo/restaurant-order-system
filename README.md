# 🍽️ Sistema de Pedidos de Restaurante — Arquitetura Distribuída

ALUNOS: Leonardo Ibiapina, Gabriel Moraes, Ênio Bazante, Cauã Jordão, João Marcolino, Victor Barbosa

## 📘 Título
**Sistema de Pedidos de Restaurante Baseado em Arquitetura Distribuída com Conceitos de Concorrência e Paralelismo**

---

## 🧾 Resumo
Este projeto apresenta uma solução prática utilizando arquitetura distribuída para o gerenciamento de pedidos em um restaurante. O sistema é composto por múltiplos serviços independentes que se comunicam por meio de um **API Gateway** e um **Message Broker**, promovendo escalabilidade, isolamento de falhas e paralelismo entre os processos. Além disso, o sistema faz uso de **threads** e **processos concorrentes** para lidar com múltiplos pedidos simultaneamente, garantindo alta performance e responsividade.

---

## 🧠 Introdução
A crescente demanda por sistemas escaláveis e confiáveis impulsionou a adoção de arquiteturas distribuídas em diversas áreas. No contexto de restaurantes, sistemas de pedidos precisam lidar com várias operações concorrentes — como registrar pedidos, processá-los na cozinha e gerenciar pagamentos — sem comprometer o desempenho.  
Este projeto tem como objetivo aplicar os conceitos estudados em **Sistemas Distribuídos**, **Concorrência** e **Paralelismo**, por meio da implementação de um sistema modular que simula o fluxo real de pedidos de um restaurante.

---

## ⚙️ Metodologia

### 🧱 Arquitetura da Solução
A solução foi projetada com base em uma **arquitetura distribuída orientada a serviços (SOA)**, composta pelos seguintes módulos:

#### 1. Aplicações de Interface
- **Aplicacao_do_Cliente:** Permite que o cliente visualize o cardápio e realize pedidos.
- **Aplicacao_do_Garcom:** Facilita o acompanhamento de pedidos e status da cozinha.

#### 2. API Gateway
Responsável por centralizar todas as requisições, encaminhando-as para o serviço apropriado. Garante segurança, roteamento e balanceamento de carga.

#### 3. Serviços Independentes
- **Servico_de_Menu:** Gerencia o cardápio, armazenado no **DB_Menu**.
- **Servico_de_Pedido:** Recebe os pedidos e grava no **DB_Pedidos**.
- **Servico_da_Cozinha:** Consome mensagens do **Message Broker** e processa pedidos em paralelo (utilizando threads).
- **Servico_de_Pagamento:** Gerencia o processamento de pagamentos, armazenando as transações no **DB_Pagamentos**.

#### 4. Message Broker
Intermedia a comunicação assíncrona entre o **Servico_de_Pedido** e o **Servico_da_Cozinha**, permitindo processamento em paralelo de múltiplos pedidos.

---

### 🔁 Concorrência e Paralelismo
- O **Servico_da_Cozinha** utiliza **threads** para processar simultaneamente diferentes pedidos.
- A comunicação entre **Servico_de_Pedido** e **Servico_da_Cozinha** é **assíncrona**, permitindo que novos pedidos sejam recebidos enquanto outros ainda estão sendo preparados.
- O sistema simula **processos independentes** (cada serviço rodando em um container ou processo distinto).
- Técnicas de **sincronização** são aplicadas para evitar condições de corrida na atualização de bancos de dados.

---

## 🚀 Como Rodar o Projeto

### 🧩 Pré-requisitos
- **Docker** e **Docker Compose** instalados.

### ▶️ Executando o Sistema

1. Clone o repositório:
   ```bash
   git clone https://github.com/ibiapleo/restaurant-order-system.git
   cd restaurant-order-system
   ```
   
2. Suba todos os serviços com o Docker Compose:
   ```bash
   docker-compose up -d
   ```
   
3. Os serviços serão inicializados automaticamente e estarão disponíveis nas portas configuradas (consulte o arquivo docker-compose.yml).

## 🌐 Acesso ao Swagger

Cada microsserviço possui sua própria documentação interativa via Swagger UI. Após rodar o sistema, acesse os seguintes endpoints no navegador:

- API Gateway: http://localhost:9090/swagger-ui/index.html?urls.primaryName=API+Gateway+Service

### Exemplo da Interface Swagger

<img width="2558" height="987" alt="image" src="https://github.com/user-attachments/assets/937d4b53-e4a1-4605-bfa4-4729fe5fbefe" />

### 🖼️ Diagrama da Arquitetura

<img width="846" height="452" alt="image" src="https://github.com/user-attachments/assets/2555c768-9ea5-49eb-aa51-873a7fd822a1" />

<img width="1600" height="535" alt="image" src="https://github.com/user-attachments/assets/31e5a2db-8a6d-444c-be96-6f4388084fee" />

---

## 🧪 Resultados

Durante os testes, o sistema demonstrou capacidade de:
- Receber múltiplos pedidos simultaneamente sem bloqueios.
- Processar pedidos em paralelo na cozinha.
- Registrar corretamente pagamentos e atualizar o status dos pedidos.

**Evidências:**
- Logs mostrando threads da cozinha processando pedidos simultaneamente.
- Banco de dados com pedidos e pagamentos sincronizados.
- Interface funcional para cliente e garçom.

---

## 🧩 Conclusão
A implementação comprovou a eficiência do uso de **arquitetura distribuída** para sistemas com múltiplas interações simultâneas.  
A divisão do sistema em serviços independentes trouxe benefícios de **modularidade, escalabilidade e desempenho**, enquanto o uso de **concorrência e paralelismo** aumentou significativamente a capacidade de processamento.  
O projeto demonstra, de forma prática, os conceitos fundamentais de **Sistemas Distribuídos** aplicados a um cenário realista.

---
