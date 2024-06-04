export const exportToXML = (data, fileName) => {
    const xmlString = `data:text/xml;chatset=utf-8,${encodeURIComponent(data)}`
    const link = document.createElement('a')
    link.href = xmlString
    link.download = `${fileName}.xml`
  
    link.click()
  }
  