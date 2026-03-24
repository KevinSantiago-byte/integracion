CREATE TABLE [PRODUCCION].[measurements]
(
    [id]            [bigint] IDENTITY (1,1) PRIMARY KEY,
    [valores]       [real]                  NULL,
    [fecha]         [datetime2](6)          NULL,
    [proceso]       [varchar](255)          NULL,
    [sensor]        [varchar](255)          NULL,
    [tipo]          [varchar](255)          NULL,
    [zona]          [varchar](255)          NULL,
    [fecha_proceso] [date]                  NULL
)

